package apisemaperreio.escalante.ajudancia.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.dtos.militarescalaveldtos.MilitarEscalavel;

@Mapper(componentModel = "spring")
public interface MilitarEscalavelMapperAjudancia {
    
    @Mapping(source = "nome.nomePaz", target = "nomePaz")
    @Mapping(source = "patente.dadoPatente", target = "patente.nome")
    @Mapping(source = "patente.dadoPatente.antiguidade", target = "patente.antiguidade")
    MilitarEscalavel toMilitarEscalanteDto(Militar militar);
    
    List<MilitarEscalavel> toListMilitarEscalanteDto(List<Militar>  militares);

}
