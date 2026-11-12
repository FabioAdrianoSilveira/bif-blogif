package bif_backend.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}

