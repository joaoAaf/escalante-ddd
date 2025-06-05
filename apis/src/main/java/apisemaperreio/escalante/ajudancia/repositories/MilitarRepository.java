package apisemaperreio.escalante.ajudancia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apisemaperreio.escalante.ajudancia.domain.Militar;

@Repository
public interface MilitarRepository extends JpaRepository<Militar, String> {

}
