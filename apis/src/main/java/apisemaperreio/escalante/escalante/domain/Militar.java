package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Militar {

    private String matricula;
    private String nomePaz;
    private LocalDate nascimento;
    private Patente patente;
    private Integer antiguidade;
    private int folgaEspecial;
    private Boolean cov;
    private List<ServicoOperacional> ultimosServicos = new ArrayList<>();

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

    public void ultimosServicosConsecutivos(List<ServicoOperacional> servicos) {
        var servicosMilitar = servicos.stream().filter(
            servico -> servico.getMatriculaMilitar().equals(this.matricula)).toList();
        if (servicosMilitar.isEmpty())
            return;
        servicosMilitar.sort(Comparator.comparing(ServicoOperacional::getDataServico).reversed());
        for (int contador = 0; contador < servicosMilitar.size(); contador++) {
            if (contador > 0 && !this.getMatricula().equals(servicosMilitar.get(contador).getMatriculaMilitar()))
                break;
            this.ultimosServicos.add(servicosMilitar.get(contador));
        }
    }

    public LocalDate dataUltimoServico() {
        return ultimosServicos.getFirst().getDataServico();
    }

    public int folgaUltimoServico() {
        return ultimosServicos.getFirst().getFolga();
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

    public List<ServicoOperacional> getUltimosServicos() {
        return ultimosServicos;
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
        return "Militar [matricula=" + matricula + ", nomePaz=" + nomePaz + ", nascimento=" + nascimento +
                ", patente=" + patente + ", antiguidade=" + antiguidade + ", folgaEspecial=" +
                folgaEspecial + ", cov=" + cov + "]";
    }

}
