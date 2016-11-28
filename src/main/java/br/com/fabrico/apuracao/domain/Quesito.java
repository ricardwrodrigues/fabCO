package br.com.fabrico.apuracao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade quesito                                                            
 */
public class Quesito implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Integer peso;

    @JsonIgnore
    private Set<Nota> notas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Quesito nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPeso() {
        return peso;
    }

    public Quesito peso(Integer peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public Quesito notas(Set<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public Quesito addNotas(Nota nota) {
        notas.add(nota);
        nota.setQuesito(this);
        return this;
    }

    public Quesito removeNotas(Nota nota) {
        notas.remove(nota);
        nota.setQuesito(null);
        return this;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quesito quesito = (Quesito) o;
        if(quesito.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, quesito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Quesito{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", peso='" + peso + "'" +
            '}';
    }
}
