package br.com.fabrico.apuracao.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Nota.                                                              
 * 
 */
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private Integer nota;

    private Quesito quesito;

    private EscolaSamba escolaSamba;

    private Jurado jurado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public Nota nota(Integer nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Quesito getQuesito() {
        return quesito;
    }

    public Nota quesito(Quesito quesito) {
        this.quesito = quesito;
        return this;
    }

    public void setQuesito(Quesito quesito) {
        this.quesito = quesito;
    }

    public EscolaSamba getEscolaSamba() {
        return escolaSamba;
    }

    public Nota escolaSamba(EscolaSamba escolaSamba) {
        this.escolaSamba = escolaSamba;
        return this;
    }

    public void setEscolaSamba(EscolaSamba escolaSamba) {
        this.escolaSamba = escolaSamba;
    }

    public Jurado getJurado() {
        return jurado;
    }

    public Nota jurado(Jurado jurado) {
        this.jurado = jurado;
        return this;
    }

    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nota nota = (Nota) o;
        if(nota.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, nota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Nota{" +
            "id=" + id +
            ", nota='" + nota + "'" +
            '}';
    }
}
