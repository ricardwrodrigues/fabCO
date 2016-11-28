package br.com.fabrico.apuracao.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidade EscolaSamba                                                        
 */
public class EscolaSamba implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Size(min = 14)
    @Pattern(regexp = "([0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2})")
    private String cnpj;

    private List<Nota> notas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public EscolaSamba nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public EscolaSamba cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
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
        EscolaSamba escolaSamba = (EscolaSamba) o;
        if(escolaSamba.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, escolaSamba.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EscolaSamba{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", cnpj='" + cnpj + "'" +
            '}';
    }
}
