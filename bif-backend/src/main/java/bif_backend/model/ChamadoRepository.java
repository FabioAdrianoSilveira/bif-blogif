package bif_backend.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

    List<Chamado> findByRemetenteUsernameOrderByDataDeCriacaoDesc(String username);

    List<Chamado> findAllByOrderByDataDeCriacaoDesc();

    List<Chamado> findByAtendidoOrderByDataDeCriacaoDesc(Boolean atendido);
}

