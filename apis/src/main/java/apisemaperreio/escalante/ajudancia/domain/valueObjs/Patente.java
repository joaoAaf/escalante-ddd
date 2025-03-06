package apisemaperreio.escalante.ajudancia.domain.valueObjs;

public class Patente {

    private Long id;
    private DadoPatente dadoPatente;
    private Integer folgaEspecial;

    public Patente(DadoPatente nomePatente, Integer folgaEspecial) {
        this.dadoPatente = nomePatente;
        this.folgaEspecial = folgaEspecial;
    }

    public Long getId() {
        return id;
    }

    public DadoPatente getDadoPatente() {
        return dadoPatente;
    }

    public void setDadoPatente(DadoPatente nomePatente) {
        this.dadoPatente = nomePatente;
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
        result = prime * result + ((dadoPatente == null) ? 0 : dadoPatente.hashCode());
        result = prime * result + ((folgaEspecial == null) ? 0 : folgaEspecial.hashCode());
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
        if (dadoPatente != other.dadoPatente)
            return false;
        if (folgaEspecial == null) {
            if (other.folgaEspecial != null)
                return false;
        } else if (!folgaEspecial.equals(other.folgaEspecial))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Patente [nomePatente=" + dadoPatente + ", folgaEspecial=" + folgaEspecial + "]";
    }

}
