package apisemaperreio.escalante.ajudancia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String profissional;

    @Column(length = 100, unique = true)
    private String pessoal;

    public Email(String profissional, String pessoal) {
        this.profissional = profissional;
        this.pessoal = pessoal;
    }

    public Email() {
    }

    public Long getId() {
        return id;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getPessoal() {
        return pessoal;
    }

    public void setPessoal(String pessoal) {
        this.pessoal = pessoal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((profissional == null) ? 0 : profissional.hashCode());
        result = prime * result + ((pessoal == null) ? 0 : pessoal.hashCode());
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
        Email other = (Email) obj;
        if (profissional == null) {
            if (other.profissional != null)
                return false;
        } else if (!profissional.equals(other.profissional))
            return false;
        if (pessoal == null) {
            if (other.pessoal != null)
                return false;
        } else if (!pessoal.equals(other.pessoal))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Email [profissional=" + profissional + ", pessoal=" + pessoal + "]";
    }

}
