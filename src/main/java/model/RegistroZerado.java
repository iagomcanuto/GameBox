package model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "notaPessoal >= 0 AND notaPessoal <= 10")
public class RegistroZerado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistroZerado;

    @Column(name = "dataFinalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "notaPessoal")
    private int notaPessoal;

    @Column(name = "comentario", length = 1000)
    private String comentario;

    public RegistroZerado() {
    }

    public RegistroZerado(LocalDate dataFinalizacao, int notaPessoal, String comentario) {
        this.dataFinalizacao = dataFinalizacao;
        this.notaPessoal = notaPessoal;
        this.comentario = comentario;
    }

    public int getIdRegistroZerado() {
        return idRegistroZerado;
    }

    public int getId() {
        return idRegistroZerado;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public int getNotaPessoal() {
        return notaPessoal;
    }

    public void setNotaPessoal(int notaPessoal) {
        this.notaPessoal = notaPessoal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
