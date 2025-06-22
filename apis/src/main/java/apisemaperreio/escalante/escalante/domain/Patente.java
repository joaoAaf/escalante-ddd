package apisemaperreio.escalante.escalante.domain;

public enum Patente {

    TEN("tenente", 1),
    SUBTEN("subtenente", 2),
    SGT("sargento", 3),
    CB("cabo", 4),
    SD("soldado", 5);

    private String nome;
    private Integer antiguidade;

    private Patente(String nome, Integer antiguidade) {
        this.nome = nome;
        this.antiguidade = antiguidade;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAntiguidade() {
        return antiguidade;
    }

}
