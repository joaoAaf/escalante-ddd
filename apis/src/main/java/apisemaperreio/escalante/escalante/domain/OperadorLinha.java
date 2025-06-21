package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Optional;

public class OperadorLinha extends ModernosPrimeiro {

    private final int FOLGA_OPERADOR_LINHA = 3;

    public OperadorLinha(LocalDate dataServico) {
        super(dataServico, Funcao.OPERADOR_DE_LINHA);
        this.folga = FOLGA_OPERADOR_LINHA;
    }

    public OperadorLinha(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.OPERADOR_DE_LINHA);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = new OperadorLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
