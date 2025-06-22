package apisemaperreio.escalante.escalante.utils.factories;

import java.util.function.Function;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.domain.Escala;

public final class EscalaFactory {

    private static Function<DadosEscala, Escala> escalaFactory = Escala::new;

    private EscalaFactory() {} 

    public static Escala criarEscala(DadosEscala dados) {
        return escalaFactory.apply(dados);
    }

}
