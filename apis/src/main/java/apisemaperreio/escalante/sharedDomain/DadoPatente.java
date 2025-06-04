package apisemaperreio.escalante.sharedDomain;

public enum DadoPatente {

    TEN("tenente", 1),
    SUBTEN("subtenente", 2),
    SGT("sargento", 3),
    CB("cabo", 4),
    SD("soldado", 5);

    private String nome;
    private Integer antiguidade;

    private DadoPatente(String nome, Integer antiguidade) {
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
