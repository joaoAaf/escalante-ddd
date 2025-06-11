package apisemaperreio.escalante.escalante.utils.factories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import apisemaperreio.escalante.escalante.domain.Cov;
import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public final class ServicoOperacionalFactory {

    private final Map<Funcao, ServicoOperacional> servicosOperacionais = new HashMap<>();
    
    private ServicoOperacionalFactory(LocalDate dataServico) {
        servicosOperacionais.put(Funcao.COV, new Cov(dataServico));
    }

    public static ServicoOperacional criarServicoOperacional(Funcao funcao, LocalDate dataServico) {
        var servicoOperacionalFactory = new ServicoOperacionalFactory(dataServico);
        var servicoOperacional = Optional.ofNullable(servicoOperacionalFactory.servicosOperacionais.get(funcao))
            .orElseThrow(() -> new IllegalArgumentException("A Função atribuida ao Serviço Operacional é inválida."));
        servicoOperacional.setDataServico(dataServico);
        return servicoOperacional;
    }

}
