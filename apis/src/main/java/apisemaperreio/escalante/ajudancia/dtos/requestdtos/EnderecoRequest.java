package apisemaperreio.escalante.ajudancia.dtos.requestdtos;

public record EnderecoRequest(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String municipio,
        String siglaUf) {

}
