package bif_backend.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

    List<Postagem> findByPublicoTrueOrderByDataDePublicacaoDesc();

    List<Postagem> findByPublicoTrueOrAutorUsernameOrderByDataDePublicacaoDesc(String username);

    List<Postagem> findAllByOrderByDataDePublicacaoDesc();

    List<Postagem> findByTituloContainingIgnoreCaseOrderByDataDePublicacaoDesc(String titulo);

    List<Postagem> findByTagsIdOrderByDataDePublicacaoDesc(Integer tagId);

    List<Postagem> findByTagsNomeIgnoreCaseOrderByDataDePublicacaoDesc(String nomeTag);
}

