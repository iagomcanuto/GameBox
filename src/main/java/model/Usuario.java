/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author iago_
 */
public class Usuario {
    
    private String nome;
    private String user;

    public Usuario(String nome, String user) {
        this.nome = nome;
        this.user = user;
    }

    public String getNome() {
        return nome;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Usuario " + nome + ", Apelido " + user;
    }
    
}
