package apisemaperreio.escalante.ajudancia.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import apisemaperreio.escalante.ajudancia.domain.Militar;

public interface MilitarAjudanciaEscalanteRepository extends JpaRepository<Militar, String> {

    @Query("SELECT m FROM Militar m LEFT JOIN FETCH m.nome LEFT JOIN FETCH m.telefone " +
                        "LEFT JOIN FETCH m.email LEFT JOIN FETCH m.endereco " +
                        "LEFT JOIN FETCH m.patente WHERE m.escalavel = true AND " +
                        "m.matricula NOT IN (SELECT a.militar.matricula FROM Ausencia a " +
                        "WHERE a.dataInicio <= :dataInicio AND a.dataFim >= :dataFim)")
        List<Militar> buscarMilitaresEscalaveis(@Param("dataInicio") LocalDate dataInicio,
                        @Param("dataFim") LocalDate dataFim);

}
