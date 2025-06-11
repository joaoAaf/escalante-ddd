package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cov extends ServicoOperacional {

    private final int FOLGA_COV = 4;

    public Cov(LocalDate dataServico) {
        super(dataServico, Funcao.COV);
    }

    public Cov(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.COV);
        this.setFolga(servicoOperacional.getFolga());
        this.setMatriculaMilitar(servicoOperacional.getMatriculaMilitar());
    }

    @Override
    public void escalarMilitar(Militar militar) {
        this.setFolga(definirFolga(militar.getFolgaEspecial(), FOLGA_COV));
        this.setMatriculaMilitar(militar.getMatricula());
        militar.getUltimosServicos().clear();
        militar.getUltimosServicos().add(this);
    }

    @Override
    public Militar buscarMilitar(List<Militar> militares) {
        var militaresCov = militares.stream().filter(militar -> militar.getCov().equals(true))
                .collect(Collectors.toList());
        var militaresCovNuncaEscalados = militaresCov.stream().filter(
                militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresCovNuncaEscalados.isEmpty()) {
            militaresCovNuncaEscalados.sort(Comparator.comparingInt(Militar::getAntiguidade));
            return Optional.of(militaresCovNuncaEscalados.getFirst()).orElseThrow();
        }
        militaresCov.sort(Comparator.comparing(Militar::dataUltimoServico));
        var militarEscalado = militaresCov.getFirst();
        var folga = militarEscalado.getUltimosServicos().size() * militarEscalado.folgaUltimoServico();
        if (militarEscalado.dataUltimoServico().plusDays(folga + 1).isBefore(this.getDataServico()))
            return Optional.of(militarEscalado).orElseThrow();
        throw new NoSuchElementException("Nenhum militar apto foi encontrado para a função de C.O.V.");
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Militar militar) {
        var proximoServico = new Cov(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        militar.getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
