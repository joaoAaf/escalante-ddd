package apisemaperreio.escalante.ajudancia.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.domain.Patente;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.PatenteRequest;

@Mapper(componentModel = "spring")
public interface MilitarMapperAjudancia {

    @Mapping(source = "nomePatente", target = "dadoPatente")
    Patente toPatente(PatenteRequest patenteRequest);
    
    Militar toMilitar(MilitarRequest militarRequest);

}