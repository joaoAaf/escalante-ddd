package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cov extends ServicoOperacional {

    private final int FOLGA_COV = 4;

    public Cov(LocalDate dataServico) {
        super(dataServico, Funcao.COV);
        this.folga = FOLGA_COV;
    }

    public Cov(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.COV);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.folga = definirFolga(militar.get().getFolgaEspecial(), this.folga);
        this.militar = militar.get();
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
        covFiscal = militar;
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        var militaresAptos = filtrarMilitaresAptos(militares);
        var militaresAptosNuncaEscalados = filtrarMilitaresAptosNuncaEscalados(militaresAptos);
        if (!militaresAptosNuncaEscalados.isEmpty())
            return filtrarMilitarAptoNuncaEscalado(militaresAptosNuncaEscalados);
        return filtrarMilitarAptoJaEscalado(militaresAptos);
    }

    private List<Militar> filtrarMilitaresAptos(List<Militar> militares) {
        return militares.stream()
                .filter(militar -> militar.getCov().equals(true))
                .collect(Collectors.toList());
    }

    private Optional<Militar> filtrarMilitarAptoNuncaEscalado(List<Militar> militares) {
        return militares.stream()
                .sorted(Comparator.comparingInt(Militar::getAntiguidade))
                .findFirst();
    }

    private Optional<Militar> filtrarMilitarAptoJaEscalado(List<Militar> militares) {
        return verificarFolgaMilitar(
                militares.stream()
                        .sorted(Comparator.comparing(Militar::dataUltimoServico)
                                .thenComparing(Comparator.comparingInt(Militar::getAntiguidade)))
                        .findFirst());
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = new Cov(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
