package apisemaperreio.escalante.escalante.utils.factories;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import apisemaperreio.escalante.escalante.domain.AjudanteLinha;
import apisemaperreio.escalante.escalante.domain.Cov;
import apisemaperreio.escalante.escalante.domain.FiscalDia;
import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.OperadorLinha;
import apisemaperreio.escalante.escalante.domain.Permanente;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public final class ServicoOperacionalFactory {

    private static final Map<Funcao, Function<LocalDate, ServicoOperacional>> FUNCOES_MAP = new EnumMap<>(Funcao.class);

    static {
        FUNCOES_MAP.put(Funcao.COV, Cov::new);
        FUNCOES_MAP.put(Funcao.PERMANENTE, Permanente::new);
        FUNCOES_MAP.put(Funcao.AJUDANTE_DE_LINHA, AjudanteLinha::new);
        FUNCOES_MAP.put(Funcao.OPERADOR_DE_LINHA, OperadorLinha::new);
        FUNCOES_MAP.put(Funcao.FISCAL_DE_DIA, FiscalDia::new);
    }

    private ServicoOperacionalFactory() {}

    public static ServicoOperacional criarServicoOperacional(Funcao funcao, LocalDate dataServico) {
        var servicoOperacional = FUNCOES_MAP.get(funcao);
        if (servicoOperacional == null) {
            throw new IllegalArgumentException("A Função atribuída ao Serviço Operacional é inválida.");
        }
        return servicoOperacional.apply(dataServico);
    }
}