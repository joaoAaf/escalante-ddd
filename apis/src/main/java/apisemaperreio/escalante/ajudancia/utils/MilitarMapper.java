package apisemaperreio.escalante.ajudancia.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.PatenteRequest;
import apisemaperreio.escalante.sharedDomain.Patente;

@Mapper(componentModel = "spring")
public interface MilitarMapper {

    @Mapping(source = "nomePatente", target = "dadoPatente")
    Patente toPatente(PatenteRequest patenteRequest);
    
    
    Militar toMilitar(MilitarRequest militarRequest);

}