package apisemaperreio.escalante.ajudancia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 8, nullable = false)
    private DadoPatente dadoPatente;

    private int folgaEspecial;

    public Patente(DadoPatente dadoPatente, Integer folgaEspecial) {
        this.dadoPatente = dadoPatente;
        this.folgaEspecial = folgaEspecial;
    }

    public Patente() {
    }

    public Long getId() {
        return id;
    }

    public DadoPatente getDadoPatente() {
        return dadoPatente;
    }

    public void setDadoPatente(DadoPatente dadoPatente) {
        this.dadoPatente = dadoPatente;
    }

    public Integer getFolgaEspecial() {
        return folgaEspecial;
    }

    public void setFolgaEspecial(Integer folgaEspecial) {
        this.folgaEspecial = folgaEspecial;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dadoPatente == null) ? 0 : dadoPatente.hashCode());
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
        Patente other = (Patente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dadoPatente != other.dadoPatente)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Patente [dadoPatente=" + dadoPatente + ", folgaEspecial=" + folgaEspecial + "]";
    }

}
