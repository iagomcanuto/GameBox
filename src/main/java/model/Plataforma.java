package model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plataforma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlataforma;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "fabricante", length = 80)
    private String fabricante;

    @OneToMany(mappedBy = "plataforma")
    private List<Jogo> jogos = new ArrayList<>();

    public Plataforma() {
    }

    public Plataforma(String nome, String fabricante) {
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public int getId() {
        return idPlataforma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void addJogo(Jogo jogo) {
        if (jogo != null && !jogos.contains(jogo)) {
            jogos.add(jogo);
            jogo.setPlataforma(this);
        }
    }

    public void removeJogo(Jogo jogo) {
        if (jogo != null && jogos.remove(jogo)) {
            jogo.setPlataforma(null);
        }
    }
}
