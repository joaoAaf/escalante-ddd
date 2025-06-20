package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Permanente extends ServicoOperacional {

    private final int FOLGA_PERMANENTE = 3;

    public Permanente(LocalDate dataServico) {
        super(dataServico, Funcao.PERMANENTE);
        this.folga = FOLGA_PERMANENTE;
    }

    public Permanente(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.PERMANENTE);
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
                ? new AjudanteLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional)
                : new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
