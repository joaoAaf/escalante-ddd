package apisemaperreio.escalante.escalante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apisemaperreio.escalante.escalante.domain.Escala;

@Repository
public interface EscalaRepositoryEscalante extends JpaRepository<Escala, Long> {

}
