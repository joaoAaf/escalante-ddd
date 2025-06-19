package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
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
        this.setMilitar(servicoOperacional.getMilitar());
    }

    @Override
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.setFolga(definirFolga(militar.get().getFolgaEspecial(), FOLGA_COV));
        this.setMilitar(militar.get());
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        var militaresCov = militares.stream()
                .filter(militar -> militar.getCov().equals(true))
                .collect(Collectors.toList());
        var militaresCovNuncaEscalados = militaresCov.stream()
                .filter(militar -> militar.getUltimosServicos().isEmpty())
                .collect(Collectors.toList());
        if (!militaresCovNuncaEscalados.isEmpty()) {
            militaresCovNuncaEscalados.sort(Comparator.comparingInt(Militar::getAntiguidade));
            return militaresCovNuncaEscalados.stream().findFirst();
        }
        militaresCov.sort(Comparator.comparing(Militar::dataUltimoServico));
        return varificarFolgaMilitar(militaresCov.stream().findFirst());
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
        var proximoServico = new Cov(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
