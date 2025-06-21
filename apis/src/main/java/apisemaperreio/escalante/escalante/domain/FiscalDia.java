package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FiscalDia extends ServicoOperacional {

    private final int FOLGA_FISCAL = 4;

    public FiscalDia(LocalDate dataServico) {
        super(dataServico, Funcao.FISCAL_DE_DIA);
        this.folga = FOLGA_FISCAL;
    }

    public FiscalDia(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.FISCAL_DE_DIA);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return verificarCovFiscal(selecionarMilitar(militares));
    }

    private Optional<Militar> verificarCovFiscal(Optional<Militar> militar) {
        if (covFiscal.isEmpty() || militar.isEmpty())
            return covFiscal.or(() -> militar);
        if (militar.get().getAntiguidade() < covFiscal.get().getAntiguidade()) {
            covFiscal = Optional.empty();
            return militar;
        }
        return covFiscal;
    }

    private Optional<Militar> selecionarMilitar(List<Militar> militares) {
        var militaresAptos = filtrarMilitaresAptos(militares);
        var militaresAptosNuncaEscalados = filtrarMilitaresAptosNuncaEscalados(militaresAptos);
        if (!militaresAptosNuncaEscalados.isEmpty())
            return filtrarMilitarAptoNuncaEscalado(militaresAptosNuncaEscalados);
        return filtrarMilitarAptoJaEscalado(militaresAptos);
    }

    private List<Militar> filtrarMilitaresAptos(List<Militar> militares) {
        return militares.stream()
                .filter(militar -> militar.getCov().equals(false))
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
        var proximoServico = new FiscalDia(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
