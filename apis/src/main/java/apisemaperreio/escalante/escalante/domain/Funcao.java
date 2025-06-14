package apisemaperreio.escalante.escalante.domain;

import java.util.Arrays;
import java.util.List;

public enum Funcao {

    COV("C.O.V.", Arrays.asList()),
    PERMANENTE("Permanente", Arrays.asList( "SD", "CB")),
    AJUDANTE_DE_LINHA("Ajudante de Linha", Arrays.asList("CB", "SD", "SGT")),
    OPERADOR_DE_LINHA("Operador de Linha", Arrays.asList("SGT", "CB", "SUBTEN", "SD")),
    FISCAL_DE_DIA("Fiscal de Dia", Arrays.asList("TEN", "SUBTEN", "SGT", "CB", "SD"));

    private String nome;
    private List<String> patentes;

    private Funcao(String nome, List<String> patentes) {
        this.nome = nome;
        this.patentes = patentes;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getPatentes() {
        return patentes;
    }

}
