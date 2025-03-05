package apisemaperreio.escalante.ajudancia.domain.valueObjs;

public enum Patente {

    TEN("Tenente", 1),
    SUBTEN("Subtenente", 2),
    SGT("Sargento", 3),
    CB("Cabo", 4),
    SD("Soldado", 5);

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
