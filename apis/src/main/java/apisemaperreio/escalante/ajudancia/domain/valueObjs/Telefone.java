package apisemaperreio.escalante.ajudancia.domain.valueObjs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import apisemaperreio.escalante.ajudancia.domain.entities.Militar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private String ddd;

    @Column(length = 10, nullable = false)
    private String numero;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "matricula_militar", nullable = false)
    @JsonIgnore
    private Militar militar;
    
    public Telefone(String ddd, String numero, Militar militar) {
        this.ddd = ddd;
        this.numero = numero;
        this.militar = militar;
    }

    public Long getId() {
        return id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Militar getMilitar() {
        return militar;
    }

    public void setMilitar(Militar militar) {
        this.militar = militar;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((militar == null) ? 0 : militar.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        if (ddd == null) {
            if (other.ddd != null)
                return false;
        } else if (!ddd.equals(other.ddd))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (militar == null) {
            if (other.militar != null)
                return false;
        } else if (!militar.equals(other.militar))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Telefone [ddd=" + ddd + ", numero=" + numero + "]";
    }

}
