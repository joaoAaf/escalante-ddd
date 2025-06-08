package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Cov extends ServicoOperacional {

    private final int FOLGA_COV = 4;

    public Cov() {
    }

    @Override
    public void escalarMilitar(List<Militar> militares, LocalDate dataServico) {
        this.setDataServico(dataServico);
        this.setFuncao(Funcao.COV);
        var militaresCov = militares.stream().filter(militar -> militar.getCov().equals(true)).toList();
        var militaresCovNuncaEscalados = militaresCov.stream().filter(
            militar -> militar.getUltimosServicos().isEmpty()).toList();
        if (!militaresCovNuncaEscalados.isEmpty()) {
            militaresCovNuncaEscalados.sort(Comparator.comparing(Militar::getAntiguidade));
            var militarEscalado = militaresCovNuncaEscalados.getFirst();
            this.setFolga(definirFolga(militarEscalado.getFolgaEspecial(), FOLGA_COV));
            this.setMatriculaMilitar(militarEscalado.getMatricula());
            militarEscalado.getUltimosServicos().add(this);
            return;
        }
        militaresCov.sort(Comparator.comparing(Militar::dataUltimoServico));
        var militarEscalado = militaresCov.getFirst();
        var folga = militarEscalado.getUltimosServicos().size() * militarEscalado.folgaUltimoServico();
        if (militarEscalado.dataUltimoServico().plusDays(folga + 1).isAfter(dataServico)) {
            this.setFolga(definirFolga(militarEscalado.getFolgaEspecial(), FOLGA_COV));
            this.setMatriculaMilitar(militarEscalado.getMatricula());
            militarEscalado.getUltimosServicos().clear();
            militarEscalado.getUltimosServicos().add(this);
        }
    }

}
