package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public Militar buscarMilitar(List<Militar> militares) {
        var militaresNaoCov = militares.stream()
                .filter(militar -> militar.getCov().equals(false) &&
                (militar.getPatente().getNome().equals("SD") || militar.getPatente().getNome().equals("CB")))
                .collect(Collectors.toList());
        var militaresNaoCovNuncaEscalados = militaresNaoCov.stream().filter(
                militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresNaoCovNuncaEscalados.isEmpty())
            return filtrarPatenteNuncaEscalado(militaresNaoCovNuncaEscalados, "SD").orElse(
                    filtrarPatenteNuncaEscalado(militaresNaoCovNuncaEscalados, "CB").orElseThrow());
        var militarEscalado = filtrarPatente(militaresNaoCov, "SD").orElse(
                filtrarPatente(militaresNaoCov, "CB").orElseThrow());
        var folga = militarEscalado.getUltimosServicos().size() * militarEscalado.folgaUltimoServico();
        if (militarEscalado.dataUltimoServico().plusDays(folga + 1).isBefore(this.getDataServico()))
            return militarEscalado;
        var militaresCov = militares.stream().filter(militar -> militar.getCov().equals(true))
                .collect(Collectors.toList());
        var militaresCovNuncaEscalados = militaresCov.stream().filter(
                militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresCovNuncaEscalados.isEmpty())
            return filtrarPatenteNuncaEscalado(militaresCovNuncaEscalados, "SD").orElse(
                    filtrarPatenteNuncaEscalado(militaresCovNuncaEscalados, "CB").orElseThrow());
        militarEscalado = filtrarPatente(militaresCov, "SD").orElse(
                filtrarPatente(militaresCov, "CB").orElseThrow());
        folga = militarEscalado.getUltimosServicos().size() * militarEscalado.folgaUltimoServico();
        if (militarEscalado.dataUltimoServico().plusDays(folga + 1).isBefore(this.getDataServico()))
            return militarEscalado;
        throw new NoSuchElementException("Nenhum militar apto foi encontrado para a função de Permanente.");
    }

    private Optional<Militar> filtrarPatenteNuncaEscalado(List<Militar> militares, String patente) {
        return militares.stream()
                .filter(militar -> militar.getPatente().getNome().equals(patente))
                .sorted(Comparator.comparingInt(Militar::getAntiguidade).reversed())
                .findFirst();
    }

    private Optional<Militar> filtrarPatente(List<Militar> militares, String patente) {
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
