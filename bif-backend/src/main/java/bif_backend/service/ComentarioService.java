package bif_backend.service;

import org.springframework.stereotype.Service;

import bif_backend.model.Comentario;
import bif_backend.model.ComentarioRepository;
import bif_backend.model.Usuario;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private Comentario comentario;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Comentario adicionarComentario(Integer postagemId, Comentario comentario, Usuario autor) {
        // TODO: Implementar regras de negócio (vincular comentário à postagem e ao autor)
        return comentarioRepository.save(comentario);
    }

    public Comentario responderComentario(Integer comentarioPaiId, Comentario resposta, Usuario autor) {
        // TODO: Implementar regras de negócio (adicionar resposta à lista respostas do comentário pai)
        return comentarioRepository.save(resposta);
    }

    public Comentario editarComentario(Integer comentarioId, String novoTexto, Usuario solicitante) {
        // TODO: Implementar regras de negócio (permitir ao autor editar o texto do próprio comentário)
        return comentarioRepository.findById(comentarioId).orElse(null);
    }

    public void removerComentario(Integer comentarioId, Usuario solicitante) {
        // TODO: Implementar regras de negócio (permitir ao autor ou administrador remover o comentário)
        comentarioRepository.deleteById(comentarioId);
    }
}

