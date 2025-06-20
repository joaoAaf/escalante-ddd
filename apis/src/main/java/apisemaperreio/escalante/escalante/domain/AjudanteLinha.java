package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class AjudanteLinha extends ServicoOperacional {

    private final int FOLGA_AJUDANTE_LINHA = 3;

    public AjudanteLinha(LocalDate dataServico) {
        super(dataServico, Funcao.AJUDANTE_DE_LINHA);
        this.folga = FOLGA_AJUDANTE_LINHA;
    }

    public AjudanteLinha(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.AJUDANTE_DE_LINHA);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return selecionarMilitar(militares, false).or(() -> selecionarMilitar(militares, true));
    }

    public Optional<Militar> selecionarMilitar(List<Militar> militares, Boolean cov) {
        var militaresAptos = filtrarMilitaresAptos(militares, cov);
        var militaresAptosNuncaEscalados = filtrarMilitaresAptosNuncaEscalados(militaresAptos);
        if (!militaresAptosNuncaEscalados.isEmpty())
            return filtrarMilitarApto(militaresAptosNuncaEscalados, this::filtrarMilitarAptoNuncaEscalado);
        return filtrarMilitarApto(militaresAptos, this::filtrarMilitarAptoJaEscalado);
    }

    private List<Militar> filtrarMilitaresAptos(List<Militar> militares, Boolean cov) {
        return militares.stream()
                .filter(militar -> militar.getCov().equals(cov) &&
                        this.funcao.getPatentes().contains(militar.getPatente()))
                .collect(Collectors.toList());
    }

    private Optional<Militar> filtrarMilitarApto(List<Militar> militares,
            BiFunction<List<Militar>, Patente, Optional<Militar>> filtro) {
        for (var patente : this.funcao.getPatentes()) {
            var militarApto = filtro.apply(militares, patente);
            if (militarApto.isPresent())
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
        return verificarFolgaMilitar(
                militares.stream()
                        .filter(militar -> militar.getPatente().equals(patente))
                        .sorted(Comparator.comparing(Militar::dataUltimoServico)
                                .thenComparing(Comparator.comparingInt(Militar::getAntiguidade).reversed()))
                        .findFirst());
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = militar.orElseThrow().getUltimosServicos().size() % 2 != 0
                ? new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional)
                : new AjudanteLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
