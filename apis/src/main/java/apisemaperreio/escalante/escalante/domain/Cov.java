package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
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
    public void escalarMilitar(List<Militar> militares) {
        var militaresCov = militares.stream().filter(militar -> militar.getCov().equals(true)).collect(Collectors.toList());
        var militaresCovNuncaEscalados = militaresCov.stream().filter(
            militar -> militar.getUltimosServicos().isEmpty()).collect(Collectors.toList());
        if (!militaresCovNuncaEscalados.isEmpty()) {
            militaresCovNuncaEscalados.sort(Comparator.comparingInt(Militar::getAntiguidade));
            var militarEscalado = militaresCovNuncaEscalados.getFirst();
            this.setFolga(definirFolga(militarEscalado.getFolgaEspecial(), FOLGA_COV));
            this.setMatriculaMilitar(militarEscalado.getMatricula());
            militarEscalado.getUltimosServicos().add(this);
            return;
        }
        militaresCov.sort(Comparator.comparing(Militar::dataUltimoServico));
        var militarEscalado = militaresCov.getFirst();
        var folga = militarEscalado.getUltimosServicos().size() * militarEscalado.folgaUltimoServico();
        if (militarEscalado.dataUltimoServico().plusDays(folga + 1).isBefore(this.getDataServico())) {
            this.setFolga(definirFolga(militarEscalado.getFolgaEspecial(), FOLGA_COV));
            this.setMatriculaMilitar(militarEscalado.getMatricula());
            militarEscalado.getUltimosServicos().clear();
            militarEscalado.getUltimosServicos().add(this);
        }
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, List<Militar> militares) {
        var proximoServico = new Cov(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        var militarEscalado = militares.stream()
                .filter(militar -> militar.getMatricula().equals(servicoOperacional.getMatriculaMilitar()))
                .findFirst().get();
        militarEscalado.getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
