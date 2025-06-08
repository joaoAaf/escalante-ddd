package apisemaperreio.escalante.escalante.domain;

public enum Funcao {

    COV("C.O.V."),
    FISCAL_DE_DIA("Fiscal de Dia"),
    OPERADOR_DE_LINHA("Operador de Linha"),
    AJUDANTE_DE_LINHA("Ajudante de Linha"),
    PERMANENTE("Permanente");

    private String nome;

    private Funcao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
