package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Cov extends AntigosPrimeiro {

    private final int FOLGA_COV = 4;

    public Cov(LocalDate dataServico) {
        super(dataServico, Funcao.COV);
        this.folga = FOLGA_COV;
    }

    public Cov(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.COV);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public void escalarMilitar(Optional<Militar> militar) {
        if (militar.isEmpty())
            return;
        this.folga = definirFolga(militar.get().getFolgaEspecial(), this.folga);
        this.militar = militar.get();
        militar.get().getUltimosServicos().clear();
        militar.get().getUltimosServicos().add(this);
        ServicoOperacional.covFiscal = militar;
    }

    @Override
    public Optional<Militar> buscarMilitar(List<Militar> militares) {
        return selecionarMilitarApto(militares, true);
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
