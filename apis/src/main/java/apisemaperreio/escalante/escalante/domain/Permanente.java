package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
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
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.setFolga(definirFolga(militar.get().getFolgaEspecial(), FOLGA_PERMANENTE));
        this.setMatriculaMilitar(militar.get().getMatricula());
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return selecionarPermanente(militares, false).or(() -> selecionarPermanente(militares, true));
    }

    public Optional<Militar> selecionarPermanente(List<Militar> militares, Boolean cov) {
        var militaresAptos = militares.stream()
                .filter(militar -> militar.getCov().equals(cov) &&
                        (militar.getPatente().getNome().equals("SD") || militar.getPatente().getNome().equals("CB")))
                .collect(Collectors.toList());
        var militaresAptosNuncaEscalados = militaresAptos.stream().filter(
                militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresAptosNuncaEscalados.isEmpty()) {
            return filtrarPatenteNuncaEscalado(militaresAptosNuncaEscalados, "SD")
                    .or(() -> filtrarPatenteNuncaEscalado(militaresAptosNuncaEscalados, "CB"));
        }
        var militarEscalado = filtrarPatenteJaEscalado(militaresAptos, "SD")
                .or(() -> filtrarPatenteJaEscalado(militaresAptos, "CB"));
        if (militarEscalado.isPresent()) {
            var folga = militarEscalado.get().getUltimosServicos().size() * militarEscalado.get().folgaUltimoServico();
            if (!militarEscalado.get().dataUltimoServico().plusDays(folga + 1).isAfter(this.getDataServico()))
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
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
