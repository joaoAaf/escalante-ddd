package apisemaperreio.escalante.escalante.domain;

public enum Funcao {

    COV("C.O.V."),
    PERMANENTE("Permanente"),
    AJUDANTE_DE_LINHA("Ajudante de Linha"),
    OPERADOR_DE_LINHA("Operador de Linha"),
    FISCAL_DE_DIA("Fiscal de Dia");

    private String nome;

    private Funcao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
