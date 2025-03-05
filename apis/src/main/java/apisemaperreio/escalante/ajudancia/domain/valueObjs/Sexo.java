package apisemaperreio.escalante.ajudancia.domain.valueObjs;

public enum Sexo {

    MASCULINO('M'),
    FEMININO('F');

    private char sexo;

    private Sexo(char sexo) {
        this.sexo = sexo;
    }

    public char getSexo() {
        return sexo;
    }

}
