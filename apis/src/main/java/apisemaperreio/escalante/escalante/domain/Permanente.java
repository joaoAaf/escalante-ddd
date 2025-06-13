package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Permanente extends ServicoOperacional {

    private final int FOLGA_PERMANENTE = 3;

    public Permanente(LocalDate dataServico) {
        super(dataServico, Funcao.PERMANENTE);
    }

    public Permanente(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.PERMANENTE);
        this.setFolga(servicoOperacional.getFolga());
        this.setMatriculaMilitar(servicoOperacional.getMatriculaMilitar());
    }

    @Override
    public void escalarMilitar(Militar militar) {
        this.setFolga(definirFolga(militar.getFolgaEspecial(), FOLGA_PERMANENTE));
        this.setMatriculaMilitar(militar.getMatricula());
        militar.getUltimosServicos().clear();
        militar.getUltimosServicos().add(this);
    }

    @Override
    public Militar selecionarMilitar(List<Militar> militares) {
       var militarSelecionado = selecionarPermanente(militares, false)
                .orElse(selecionarPermanente(militares, true)
                        .orElseThrow(() -> new NoSuchElementException("Nenhum militar apto foi encontrado para a função de Permanente.")));
        return militarSelecionado;
    }

    private Optional<Militar> selecionarPermanente(List<Militar> militares, Boolean cov) {
        var militaresAptos = militares.stream()
                .filter(militar -> militar.getCov().equals(cov) &&
                        (militar.getPatente().getNome().equals("SD") || militar.getPatente().getNome().equals("CB")))
                .collect(Collectors.toList());
        var militaresAptosNuncaEscalados = militaresAptos.stream().filter(
                militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresAptosNuncaEscalados.isEmpty())
            return filtrarMilitarApto(militaresAptosNuncaEscalados, this::filtrarPatenteNuncaEscalado);
        var militarEscalado = filtrarMilitarApto(militaresAptos, this::filtrarPatenteJaEscalado);
        var folga = militarEscalado.get().getUltimosServicos().size() * militarEscalado.get().folgaUltimoServico();
        if (militarEscalado.get().dataUltimoServico().plusDays(folga + 1).isBefore(this.getDataServico()))
            return militarEscalado;
        return Optional.empty();
    }

    private Optional<Militar> filtrarMilitarApto(List<Militar> militares,
            BiFunction<List<Militar>, String, Optional<Militar>> filtro) {
        for (var patente : Funcao.PERMANENTE.getPatentes()) {
            var militarEscalado = filtro.apply(militares, patente);
            if (militarEscalado.isEmpty()) {
                continue;
            }
            return militarEscalado;
        }
        return Optional.empty();
    }

    private Optional<Militar> filtrarPatenteNuncaEscalado(List<Militar> militares, String patente) {
        return militares.stream()
                .filter(militar -> militar.getPatente().getNome().equals(patente))
                .sorted(Comparator.comparingInt(Militar::getAntiguidade).reversed())
                .findFirst();
    }

    private Optional<Militar> filtrarPatenteJaEscalado(List<Militar> militares, String patente) {
        return militares.stream()
                .filter(militar -> militar.getPatente().getNome().equals(patente))
                .sorted(Comparator.comparing(Militar::dataUltimoServico)
                        .thenComparing(Comparator.comparingInt(Militar::getAntiguidade).reversed()))
                .findFirst();
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Militar militar) {
        var proximoServico = new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        militar.getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
