package bif_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bif_backend.model.Postagem;
import bif_backend.model.PostagemRepository;
import bif_backend.model.Usuario;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private Postagem postagem;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public Postagem criarPostagem(Postagem postagem, Usuario autor) {
        // TODO: Implementar regras de negócio (vincular autor, dataDePublicacao = LocalDate.now(), tags)
        return postagemRepository.save(postagem);
    }

    public Postagem editarPostagem(Integer id, Postagem dados, Usuario solicitante) {
        // TODO: Implementar regras de negócio (verificar autoria e atualizar campos)
        return postagemRepository.save(dados);
    }

    public void excluirPostagem(Integer id, Usuario solicitante) {
        // TODO: Implementar regras de negócio (verificar se solicitante é o autor)
        postagemRepository.deleteById(id);
    }

    public Postagem acessarPostagem(Integer id, Usuario solicitanteOpcional) {
        // TODO: Implementar regras de negócio (verificar visibilidade pública/autor/administrador)
        return postagemRepository.findById(id).orElse(null);
    }

    public List<Postagem> buscarPostagens(
            String titulo,
            Integer tagId,
            boolean ordenarPorMaisRecentes,
            Usuario solicitanteOpcional
    ) {
        // TODO: Implementar regras de negócio (filtros por título, tag, mais recentes e visibilidade por ator)
        return postagemRepository.findAllByOrderByDataDePublicacaoDesc();
    }

    public Postagem moderarPostagem(Integer id, Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador; definir publico = false)
        return postagemRepository.findById(id).orElse(null);
    }
}

