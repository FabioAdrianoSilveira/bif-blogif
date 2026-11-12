package bif_backend.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import bif_backend.model.Postagem;
import bif_backend.model.Usuario;

public record PostagemDTO(
        Integer id,
        String capaUrl,
        String titulo,
        String conteudo,
        LocalDate dataDePublicacao,
        Boolean publico,
        String autorUsername,
        List<TagDTO> tags,
        List<ComentarioDTO> comentarios
) {

    public static PostagemDTO fromEntity(Postagem postagem) {
        if (postagem == null) {
            return null;
        }
        List<TagDTO> tagsDto = postagem.getTags() != null
                ? postagem.getTags().stream().map(TagDTO::fromEntity).toList()
                : Collections.emptyList();
        List<ComentarioDTO> comentariosDto = postagem.getComentarios() != null
                ? postagem.getComentarios().stream().map(ComentarioDTO::fromEntity).toList()
                : Collections.emptyList();

        return new PostagemDTO(
                postagem.getId(),
                postagem.getCapaUrl(),
                postagem.getTitulo(),
                postagem.getConteudo(),
                postagem.getDataDePublicacao(),
                postagem.getPublico(),
                postagem.getAutor() != null ? postagem.getAutor().getUsername() : null,
                tagsDto,
                comentariosDto
        );
    }

    public Postagem toEntity() {
        Postagem postagem = new Postagem();
        postagem.setId(this.id);
        postagem.setCapaUrl(this.capaUrl);
        postagem.setTitulo(this.titulo);
        postagem.setConteudo(this.conteudo);
        postagem.setDataDePublicacao(this.dataDePublicacao);
        postagem.setPublico(this.publico);
        if (this.autorUsername != null) {
            Usuario autor = new Usuario();
            autor.setUsername(this.autorUsername);
            postagem.setAutor(autor);
        }
        if (this.tags != null) {
            postagem.setTags(this.tags.stream().map(TagDTO::toEntity).toList());
        }
        if (this.comentarios != null) {
            postagem.setComentarios(this.comentarios.stream().map(ComentarioDTO::toEntity).toList());
        }
        return postagem;
    }
}

