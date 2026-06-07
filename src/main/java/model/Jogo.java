/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


@Entity
public class Jogo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJogo;

    @Column(name = "titulo", nullable = false, length = 120)
    private String titulo;

    @Column(name = "anoLancamento")
    private int anoLancamento;

    @Column(name = "caminhoCapa", length = 255)
    private String caminhoCapa;

    @Column(name = "tempoJogo", length = 30)
    private String tempoJogo;

    @Column(name = "dificuldade", length = 30)
    private String dificuldade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusProgresso status = StatusProgresso.BACKLOG;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPlataforma")
    private Plataforma plataforma;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JogoGeneroBD",
            joinColumns = @JoinColumn(name = "idJogo"),
            inverseJoinColumns = @JoinColumn(name = "idGenero")
    )
    private List<Genero> generos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRegistroZerado")
    private RegistroZerado registroZerado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Jogo() {
    }

    public Jogo(String nome, String plataforma, String tempoJogo, String dificuldade, String avaliacao, String comentario) {
        this.titulo = nome;
        this.plataforma = plataforma == null || plataforma.isBlank() ? null : new Plataforma(plataforma, "");
        this.tempoJogo = tempoJogo;
        this.dificuldade = dificuldade;
        this.status = StatusProgresso.BACKLOG;

        int nota = 0;
        if (avaliacao != null && !avaliacao.isBlank()) {
            try {
                nota = Integer.parseInt(avaliacao.trim());
            } catch (NumberFormatException ignored) {
                nota = 0;
            }
        }
        // Mantém compatibilidade com a UI atual (avaliação + comentário) usando RegistroZerado.
        this.registroZerado = (comentario == null || comentario.isBlank()) && nota == 0
                ? null
                : new RegistroZerado((LocalDate) null, nota, comentario);
    }

    public Jogo(String titulo, int anoLancamento, String caminhoCapa, StatusProgresso status) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.caminhoCapa = caminhoCapa;
        this.status = status;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public int getId() {
        return idJogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCaminhoCapa() {
        return caminhoCapa;
    }

    public void setCaminhoCapa(String caminhoCapa) {
        this.caminhoCapa = caminhoCapa;
    }

    public String getTempoJogo() {
        return tempoJogo;
    }

    public void setTempoJogo(String tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public StatusProgresso getStatus() {
        return status;
    }

    public void setStatus(StatusProgresso status) {
        this.status = status;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public void addGenero(Genero genero) {
        if (genero != null && !generos.contains(genero)) {
            generos.add(genero);
            genero.getJogos().add(this);
        }
    }

    public void removeGenero(Genero genero) {
        if (genero != null && generos.remove(genero)) {
            genero.getJogos().remove(this);
        }
    }

    public RegistroZerado getRegistroZerado() {
        return registroZerado;
    }

    public void setRegistroZerado(RegistroZerado registroZerado) {
        this.registroZerado = registroZerado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
