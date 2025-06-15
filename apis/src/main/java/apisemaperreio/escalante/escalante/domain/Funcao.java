package apisemaperreio.escalante.escalante.domain;

import java.util.Arrays;
import java.util.List;

public enum Funcao {

    COV("C.O.V.", Arrays.asList()),
    PERMANENTE("Permanente", Arrays.asList( Patente.SD, Patente.CB)),
    AJUDANTE_DE_LINHA("Ajudante de Linha", Arrays.asList(Patente.CB, Patente.SD, Patente.SGT)),
    OPERADOR_DE_LINHA("Operador de Linha", Arrays.asList(Patente.SGT, Patente.CB, Patente.SUBTEN, Patente.SD)),
    FISCAL_DE_DIA("Fiscal de Dia", Arrays.asList(Patente.TEN, Patente.SUBTEN, Patente.SGT, Patente.CB, Patente.SD));

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
