package apisemaperreio.escalante.ajudancia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, length = 50)
    private String nomePaz;

    public Nome(String nomeCompleto, String nomePaz) {
        this.nomeCompleto = nomeCompleto;
        this.nomePaz = nomePaz;
    }

    public Nome() {
    }

    // public Nome(String nomeCompleto, String nomePaz) {
    // if (verificaNomePaz(nomeCompleto, nomePaz)) {
    // this.nomeCompleto = nomeCompleto;
    // this.nomePaz = nomePaz;
    // }
    // throw new IllegalArgumentException("Nome de Paz inválido");
    // }

    // public Boolean verificaNomePaz(String nomeCompleto, String nomePaz) {
    // var nomeCompletoArray = Arrays.asList(nomeCompleto.split(" "));
    // var nomePazArray = Arrays.asList(nomePaz.split(" "));
    // return nomePazArray.stream().allMatch(nomeCompletoArray::contains);
    // }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomePaz() {
        return nomePaz;
    }

    public void setNomePaz(String nomePaz) {
        this.nomePaz = nomePaz;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Nome other = (Nome) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Nome [nomeCompleto=" + nomeCompleto + ", nomePaz=" + nomePaz + "]";
    }

}