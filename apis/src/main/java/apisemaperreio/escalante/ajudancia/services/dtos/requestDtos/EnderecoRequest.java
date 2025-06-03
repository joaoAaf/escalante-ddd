package apisemaperreio.escalante.ajudancia.services.dtos.requestDtos;

public record EnderecoRequest(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String municipio,
        String siglaUf) {

}
