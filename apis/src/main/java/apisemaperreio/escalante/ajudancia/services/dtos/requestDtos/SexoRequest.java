package apisemaperreio.escalante.ajudancia.services.dtos.requestDtos;

public enum SexoRequest {

    M('M'),
    F('F');

    private char sexo;

    private SexoRequest(char sexo) {
        this.sexo = sexo;
    }

    public char getSexo() {
        return sexo;
    }

}
