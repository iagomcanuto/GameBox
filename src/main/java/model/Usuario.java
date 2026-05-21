/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 *
 * @author iago_
 */
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "senha", nullable = false, length = 120)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jogo> biblioteca = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getId() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Jogo> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<Jogo> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void addJogo(Jogo jogo) {
        if (jogo != null && !biblioteca.contains(jogo)) {
            biblioteca.add(jogo);
            jogo.setUsuario(this);
        }
    }

    public void removeJogo(Jogo jogo) {
        if (jogo != null && biblioteca.remove(jogo)) {
            jogo.setUsuario(null);
        }
    }

    public double calcularMediaNotas() {
        return biblioteca.stream()
                .map(Jogo::getRegistroZerado)
                .filter(registro -> registro != null)
                .mapToInt(RegistroZerado::getNotaPessoal)
                .average()
                .orElse(0.0);
    }
    public int getTotalJogosZerados() {
        return (int) biblioteca.stream()
                .filter(jogo -> jogo.getStatus() == StatusProgresso.ZERADO)
                .count();
    }

    @Override
    public String toString() {
        return "Usuario " + nome;
    }
    
}
