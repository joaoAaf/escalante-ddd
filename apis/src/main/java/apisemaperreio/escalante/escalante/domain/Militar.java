package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;

import apisemaperreio.escalante.sharedDomain.Patente;

public class Militar {

    private String matricula;
    private String nomePaz;
    private LocalDate nascimento;
    private Patente patente;
    private Integer antiguidade;
    private int folgaEspecial;
    private Boolean cov;
    
    public Militar(String matricula, String nomePaz, LocalDate nascimento, Patente patente, Integer antiguidade,
            int folgaEspecial, Boolean cov) {
        this.matricula = matricula;
        this.nomePaz = nomePaz;
        this.nascimento = nascimento;
        this.patente = patente;
        this.antiguidade = antiguidade;
        this.folgaEspecial = folgaEspecial;
        this.cov = cov;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomePaz() {
        return nomePaz;
    }

    public void setNomePaz(String nomePaz) {
        this.nomePaz = nomePaz;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Integer getAntiguidade() {
        return antiguidade;
    }

    public void setAntiguidade(Integer antiguidade) {
        this.antiguidade = antiguidade;
    }

    public int getFolgaEspecial() {
        return folgaEspecial;
    }

    public void setFolgaEspecial(int folgaEspecial) {
        this.folgaEspecial = folgaEspecial;
    }

    public Boolean getCov() {
        return cov;
    }

    public void setCov(Boolean cov) {
        this.cov = cov;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
        Militar other = (Militar) obj;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Militar [matricula=" + matricula + ", nomePaz=" + nomePaz + ", nascimento=" + nascimento + ", patente="
                + patente + ", antiguidade=" + antiguidade + "]";
    }

}
