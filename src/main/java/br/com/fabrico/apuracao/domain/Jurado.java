package br.com.fabrico.apuracao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Jurado                                                             
 */
public class Jurado implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Size(min = 11)
    @Pattern(regexp = "([0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2})")
    private String cpf;

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

    public Jurado nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Jurado cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public Jurado notas(Set<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public Jurado addNotas(Nota nota) {
        notas.add(nota);
        nota.setJurado(this);
        return this;
    }

    public Jurado removeNotas(Nota nota) {
        notas.remove(nota);
        nota.setJurado(null);
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
        Jurado jurado = (Jurado) o;
        if(jurado.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, jurado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Jurado{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", cpf='" + cpf + "'" +
            '}';
    }
}
