package apisemaperreio.escalante.ajudancia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private String ddd1;

    @Column(length = 9, nullable = false)
    private String numero1;

    @Column(length = 2)
    private String ddd2;

    @Column(length = 9)
    private String numero2;

    public Telefone(String ddd1, String numero1, String ddd2, String numero2) {
        this.ddd1 = ddd1;
        this.numero1 = numero1;
        this.ddd2 = ddd2;
        this.numero2 = numero2;
    }
    
    public Telefone() {
    }

    public Long getId() {
        return id;
    }

    public String getDdd1() {
        return ddd1;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = ddd1;
    }

    public String getNumero1() {
        return numero1;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ddd1 == null) ? 0 : ddd1.hashCode());
        result = prime * result + ((numero1 == null) ? 0 : numero1.hashCode());
        result = prime * result + ((ddd2 == null) ? 0 : ddd2.hashCode());
        result = prime * result + ((numero2 == null) ? 0 : numero2.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ddd1 == null) {
            if (other.ddd1 != null)
                return false;
        } else if (!ddd1.equals(other.ddd1))
            return false;
        if (numero1 == null) {
            if (other.numero1 != null)
                return false;
        } else if (!numero1.equals(other.numero1))
            return false;
        if (ddd2 == null) {
            if (other.ddd2 != null)
                return false;
        } else if (!ddd2.equals(other.ddd2))
            return false;
        if (numero2 == null) {
            if (other.numero2 != null)
                return false;
        } else if (!numero2.equals(other.numero2))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Telefone [ddd1=" + ddd1 + ", numero1=" + numero1 + ", ddd2=" + ddd2 + ", numero2=" + numero2 + "]";
    }

}
