package apisemaperreio.escalante.ajudancia.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.domain.Patente;
import apisemaperreio.escalante.ajudancia.services.dtos.requestdtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestdtos.PatenteRequest;

@Mapper(componentModel = "spring")
public interface MilitarMapper {

    @Mapping(source = "nomePatente", target = "dadoPatente")
    Patente toPatente(PatenteRequest patenteRequest);
    
    Militar toMilitar(MilitarRequest militarRequest);

}