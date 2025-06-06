package apisemaperreio.escalante.escalante.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.dtos.militarescalaveldtos.MilitarEscalavel;
import apisemaperreio.escalante.escalante.domain.Militar;

@Mapper(componentModel = "spring")
public interface MilitarMapperEscalante {

    @Mapping(target = "ultimoServico", ignore = true)
    Militar toMilitar(MilitarEscalavel militarEscalavel);

    List<Militar> toListMilitar(List<MilitarEscalavel> militaresEscalaveis);

}
