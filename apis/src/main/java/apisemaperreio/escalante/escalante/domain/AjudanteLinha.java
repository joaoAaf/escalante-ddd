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
    }

    public AjudanteLinha(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.AJUDANTE_DE_LINHA);
        this.setFolga(servicoOperacional.getFolga());
        this.setMatriculaMilitar(servicoOperacional.getMatriculaMilitar());
    }

    @Override
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.setFolga(definirFolga(militar.get().getFolgaEspecial(), FOLGA_AJUDANTE_LINHA));
        this.setMatriculaMilitar(militar.get().getMatricula());
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return selecionarAjudanteLinha(militares, false).or(() -> selecionarAjudanteLinha(militares, true));
    }

    public Optional<Militar> selecionarAjudanteLinha(List<Militar> militares, Boolean cov) {
        var militaresAptos = militares.stream()
                .filter(militar -> militar.getCov().equals(cov) &&
                        Funcao.AJUDANTE_DE_LINHA.getPatentes().contains(militar.getPatente()))
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
        for (var patente : Funcao.AJUDANTE_DE_LINHA.getPatentes()) {
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
        var proximoServico = militar.orElseThrow().getUltimosServicos().size() % 2 != 0
                ? new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional)
                : new AjudanteLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
