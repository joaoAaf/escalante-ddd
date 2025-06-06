package apisemaperreio.escalante.ajudancia.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.sharedcore.ajudancia_escalante.dtos.MilitarAjudanciaEscalante;

@Mapper(componentModel = "spring")
public interface MilitarAjudanciaEscalanteMapper {
    
    @Mapping(source = "nome.nomePaz", target = "nomePaz")
    @Mapping(source = "patente.dadoPatente", target = "patente.nome")
    @Mapping(source = "patente.dadoPatente.antiguidade", target = "patente.antiguidade")
    MilitarAjudanciaEscalante toMilitarEscalanteDto(Militar militar);
    
    List<MilitarAjudanciaEscalante> toListMilitarEscalanteDto(List<Militar>  militares);

}
