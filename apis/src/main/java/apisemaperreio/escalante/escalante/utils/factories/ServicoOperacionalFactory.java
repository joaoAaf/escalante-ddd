package apisemaperreio.escalante.escalante.utils.factories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import apisemaperreio.escalante.escalante.domain.AjudanteLinha;
import apisemaperreio.escalante.escalante.domain.Cov;
import apisemaperreio.escalante.escalante.domain.FiscalDia;
import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.OperadorLinha;
import apisemaperreio.escalante.escalante.domain.Permanente;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public final class ServicoOperacionalFactory {

    private final Map<Funcao, ServicoOperacional> servicosOperacionais = new HashMap<>();
    
    private ServicoOperacionalFactory(LocalDate dataServico) {
        servicosOperacionais.put(Funcao.COV, new Cov(dataServico));
        servicosOperacionais.put(Funcao.PERMANENTE, new Permanente(dataServico));
        servicosOperacionais.put(Funcao.AJUDANTE_DE_LINHA, new AjudanteLinha(dataServico));
        servicosOperacionais.put(Funcao.OPERADOR_DE_LINHA, new OperadorLinha(dataServico));
        servicosOperacionais.put(Funcao.FISCAL_DE_DIA, new FiscalDia(dataServico));
    }

    public static ServicoOperacional criarServicoOperacional(Funcao funcao, LocalDate dataServico) {
        var servicoOperacionalFactory = new ServicoOperacionalFactory(dataServico);
        var servicoOperacional = Optional.ofNullable(servicoOperacionalFactory.servicosOperacionais.get(funcao))
            .orElseThrow(() -> new IllegalArgumentException("A Função atribuida ao Serviço Operacional é inválida."));
        return servicoOperacional;
    }

}
