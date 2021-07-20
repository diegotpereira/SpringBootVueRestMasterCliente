package br.com.java.springbootrest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.springbootrest.model.Cliente;
import br.com.java.springbootrest.repository.ClienteRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteController {
    
    @Autowired
    ClienteRepository repository;

    @GetMapping("/clientes")
    public List buscarTodosClientes() {
        System.out.println("Buscar todos os clientes...");

        List clientes = new ArrayList();
        repository.findAll().forEach(clientes::add);

        return clientes;
    }

    @PostMapping(value="/cliente")
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        //TODO: process POST request
        Cliente _cliente = repository.save(new Cliente(cliente.getNome(), cliente.getIdade()));
        
        return _cliente;
    }

    @DeleteMapping(value = "/cliente/{id}")
    public ResponseEntity deletarCliente(@PathVariable("id") long id) {
        System.out.println("Excluir o cliente com ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity("Cliente deletado com sucesso!.", HttpStatus.OK);

    }
    
    @GetMapping("clientes/idade/{idade}")
    public List buscaPorIdade(@PathVariable int idade) {
        List clientes = repository.findByIdade(idade);

        return clientes;
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity atualizarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        System.out.println("Atualizar Cliente com ID = " + id + "...");

        Optional clienteData = repository.findById(id);

        if (clienteData.isPresent()) {
            Cliente _cliente = (Cliente) clienteData.get();
            _cliente.setNome(cliente.getNome());
            _cliente.setIdade(cliente.getIdade());
            _cliente.setAtivo(cliente.isAtivo());

            return new ResponseEntity(repository.save(_cliente), HttpStatus.OK);
        } else {
            
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
