package model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

    @Column(name = "nome", nullable = false, unique = true, length = 80)
    private String nome;

    @ManyToMany(mappedBy = "generos")
    private List<Jogo> jogos = new ArrayList<>();

    public Genero() {
    }

    public Genero(String nome) {
        this.nome = nome;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public int getId() {
        return idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
}
