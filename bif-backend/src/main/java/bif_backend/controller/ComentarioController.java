package bif_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bif_backend.dto.ComentarioDTO;
import bif_backend.model.Comentario;
import bif_backend.model.ComentarioRepository;
import bif_backend.model.Usuario;
import bif_backend.service.ComentarioService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ComentarioRepository comentarioRepository;
    private ComentarioDTO comentarioDTO;

    public ComentarioController(
            ComentarioService comentarioService,
            ComentarioRepository comentarioRepository
    ) {
        this.comentarioService = comentarioService;
        this.comentarioRepository = comentarioRepository;
    }

    @PostMapping("/postagens/{postagemId}/comentarios")
    public ResponseEntity<ComentarioDTO> comentar(
            @PathVariable Integer postagemId,
            @Valid @RequestBody ComentarioDTO dto
    ) {
        Comentario entidade = dto.toEntity();
        Comentario criado = comentarioService.adicionarComentario(postagemId, entidade, entidade.getAutor());
        return ResponseEntity.ok(ComentarioDTO.fromEntity(criado));
    }

    @PostMapping("/comentarios/{comentarioId}/respostas")
    public ResponseEntity<ComentarioDTO> responder(
            @PathVariable Integer comentarioId,
            @Valid @RequestBody ComentarioDTO dto
    ) {
        Comentario entidade = dto.toEntity();
        Comentario respondido = comentarioService.responderComentario(comentarioId, entidade, entidade.getAutor());
        return ResponseEntity.ok(ComentarioDTO.fromEntity(respondido));
    }

    @PutMapping("/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> editar(
            @PathVariable Integer comentarioId,
            @Valid @RequestBody ComentarioDTO dto
    ) {
        Comentario entidade = dto.toEntity();
        Comentario editado = comentarioService.editarComentario(comentarioId, dto.texto(), entidade.getAutor());
        return ResponseEntity.ok(ComentarioDTO.fromEntity(editado));
    }

    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer comentarioId,
            @RequestParam String usernameSolicitante
    ) {
        Usuario solicitante = new Usuario();
        solicitante.setUsername(usernameSolicitante);
        comentarioService.removerComentario(comentarioId, solicitante);
        return ResponseEntity.noContent().build();
    }
}

