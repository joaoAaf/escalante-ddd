package apisemaperreio.escalante.ajudancia.domain.valueObjs;

public record Municipio(
        Long id,
        String nome,
        String siglaUf) {

    public Municipio(String nome, String siglaUf) {
        this(null, nome, siglaUf);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((siglaUf == null) ? 0 : siglaUf.hashCode());
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
        Municipio other = (Municipio) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (siglaUf == null) {
            if (other.siglaUf != null)
                return false;
        } else if (!siglaUf.equals(other.siglaUf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Municipio [nome=" + nome + ", siglaUf=" + siglaUf + "]";
    }

}
