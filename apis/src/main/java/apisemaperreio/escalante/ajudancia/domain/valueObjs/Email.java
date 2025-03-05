package apisemaperreio.escalante.ajudancia.domain.valueObjs;

public record Email(
        Long id,
        String profissional,
        String pessoal) {

    public Email(String profissional, String pessoal) {
        this(null, profissional, pessoal);
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
