package bif_backend.dto;

import java.util.Collections;
import java.util.List;

import bif_backend.model.Comentario;
import bif_backend.model.Usuario;

public record ComentarioDTO(
        Integer id,
        String texto,
        String autorUsername,
        List<ComentarioDTO> respostas
) {

    public static ComentarioDTO fromEntity(Comentario comentario) {
        if (comentario == null) {
            return null;
        }
        List<ComentarioDTO> respostasDto = comentario.getRespostas() != null
                ? comentario.getRespostas().stream().map(ComentarioDTO::fromEntity).toList()
                : Collections.emptyList();

        return new ComentarioDTO(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getAutor() != null ? comentario.getAutor().getUsername() : null,
                respostasDto
        );
    }

    public Comentario toEntity() {
        Comentario comentario = new Comentario();
        comentario.setId(this.id);
        comentario.setTexto(this.texto);
        if (this.autorUsername != null) {
            Usuario autor = new Usuario();
            autor.setUsername(this.autorUsername);
            comentario.setAutor(autor);
        }
        if (this.respostas != null) {
            comentario.setRespostas(this.respostas.stream().map(ComentarioDTO::toEntity).toList());
        }
        return comentario;
    }
}

