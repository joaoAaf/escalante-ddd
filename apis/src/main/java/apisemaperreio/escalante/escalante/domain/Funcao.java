package apisemaperreio.escalante.escalante.domain;

import java.util.Arrays;
import java.util.List;

public enum Funcao {

    FISCAL_DE_DIA("Fiscal de Dia", Arrays.asList(Patente.TEN, Patente.SUBTEN, Patente.SGT, Patente.CB, Patente.SD)),
    COV("C.O.V.", Arrays.asList()),
    PERMANENTE("Permanente", Arrays.asList( Patente.SD, Patente.CB)),
    AJUDANTE_DE_LINHA("Ajudante de Linha", Arrays.asList(Patente.CB, Patente.SD)),
    OPERADOR_DE_LINHA("Operador de Linha", Arrays.asList(Patente.CB, Patente.SD, Patente.SGT, Patente.SUBTEN));

    private String nome;
    private List<Patente> patentes;

    private Funcao(String nome, List<Patente> patentes) {
        this.nome = nome;
        this.patentes = patentes;
    }

    public String getNome() {
        return nome;
    }

    public List<Patente> getPatentes() {
        return patentes;
    }

}
