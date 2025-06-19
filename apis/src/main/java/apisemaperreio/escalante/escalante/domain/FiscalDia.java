package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class FiscalDia extends ServicoOperacional {

    private final int FOLGA_FISCAL = 4;

    public FiscalDia(LocalDate dataServico) {
        super(dataServico, Funcao.FISCAL_DE_DIA);
    }

    public FiscalDia(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.FISCAL_DE_DIA);
        this.setFolga(servicoOperacional.getFolga());
        this.setMilitar(servicoOperacional.getMilitar());
    }

    @Override
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.setFolga(definirFolga(militar.get().getFolgaEspecial(), FOLGA_FISCAL));
        this.setMilitar(militar.get());
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return selecionarFiscal(militares, false).or(() -> selecionarFiscal(militares, true));
    }

    private Optional<Militar> selecionarFiscal(List<Militar> militares, Boolean cov) {
        var militaresAptos = militares.stream()
                .filter(militar -> militar.getCov().equals(cov) &&
                        Funcao.FISCAL_DE_DIA.getPatentes().contains(militar.getPatente()))
                .collect(Collectors.toList());
        var militaresAptosNuncaEscalados = militaresAptos.stream()
                .filter(militar -> militar.getUltimosServicos().isEmpty())
                .collect(Collectors.toList());
        if (!militaresAptosNuncaEscalados.isEmpty())
            return filtrarMilitarApto(militaresAptosNuncaEscalados, this::filtrarMilitarAptoNuncaEscalado);
        return filtrarMilitarApto(militaresAptos, this::filtrarMilitarAptoJaEscalado);
    }

    private Optional<Militar> filtrarMilitarApto(List<Militar> militares,
            BiFunction<List<Militar>, Patente, Optional<Militar>> filtro) {
        for (var patente : Funcao.FISCAL_DE_DIA.getPatentes()) {
            var militarApto = filtro.apply(militares, patente);
            if (militarApto.isEmpty())
                continue;
            return militarApto;
        }
        return Optional.empty();
    }

    private Optional<Militar> filtrarMilitarAptoNuncaEscalado(List<Militar> militares, Patente patente) {
        return militares.stream()
                .filter(militar -> militar.getPatente().equals(patente))
                .sorted(Comparator.comparingInt(Militar::getAntiguidade).reversed())
                .findFirst();
    }

    private Optional<Militar> filtrarMilitarAptoJaEscalado(List<Militar> militares, Patente patente) {
        return varificarFolgaMilitar(
                militares.stream()
                        .filter(militar -> militar.getPatente().equals(patente))
                        .sorted(Comparator.comparing(Militar::dataUltimoServico)
                                .thenComparing(Comparator.comparingInt(Militar::getAntiguidade).reversed()))
                        .findFirst());
    }

    private Optional<Militar> varificarFolgaMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return militar;
        var militarVerificado = militar.get();
        var folga = militarVerificado.getUltimosServicos().size() * militarVerificado.folgaUltimoServico();
        return militarVerificado.dataUltimoServico().plusDays(folga + 1).isAfter(this.getDataServico())
                ? Optional.empty()
                : militar;
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
