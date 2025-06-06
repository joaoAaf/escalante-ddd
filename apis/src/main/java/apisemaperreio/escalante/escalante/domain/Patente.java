package apisemaperreio.escalante.escalante.domain;

public class Patente {

    private String nome;
    private Integer antiguidade;

    public Patente(String nome, Integer antiguidade) {
        this.nome = nome;
        this.antiguidade = antiguidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAntiguidade() {
        return antiguidade;
    }

    public void setAntiguidade(Integer antiguidade) {
        this.antiguidade = antiguidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((antiguidade == null) ? 0 : antiguidade.hashCode());
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
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (antiguidade == null) {
            if (other.antiguidade != null)
                return false;
        } else if (!antiguidade.equals(other.antiguidade))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Patente [nome=" + nome + ", antiguidade=" + antiguidade + "]";
    }

}
