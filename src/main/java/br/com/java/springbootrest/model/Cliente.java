package br.com.java.springbootrest.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "ativo")
    private boolean ativo;

    public Cliente() {
    }

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.ativo = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Cliente [ativo=" + ativo + ", id=" + id + ", idade=" + idade + ", nome=" + nome + "]";
    }
}
