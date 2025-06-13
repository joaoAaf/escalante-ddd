package apisemaperreio.escalante.escalante.domain;

public enum Funcao {

    COV("C.O.V.", new String[]{}),
    PERMANENTE("Permanente", new String[]{"SD", "CB"}),
    AJUDANTE_DE_LINHA("Ajudante de Linha", new String[]{"CB", "SD", "SGT"}),
    OPERADOR_DE_LINHA("Operador de Linha", new String[]{"SGT", "CB", "SUBTEN", "SD"}),
    FISCAL_DE_DIA("Fiscal de Dia", new String[]{"TEN", "SUBTEN", "SGT", "CB", "SD"});

    private String nome;
    private String[] patentes;

    private Funcao(String nome, String[] patentes) {
        this.nome = nome;
        this.patentes = patentes;
    }

    public String getNome() {
        return nome;
    }

    public String[] getPatentes() {
        return patentes;
    }

}
