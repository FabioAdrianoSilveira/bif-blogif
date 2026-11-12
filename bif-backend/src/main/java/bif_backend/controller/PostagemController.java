package bif_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bif_backend.dto.PostagemDTO;
import bif_backend.model.Postagem;
import bif_backend.model.PostagemRepository;
import bif_backend.model.Usuario;
import bif_backend.service.PostagemService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class PostagemController {

    private final PostagemService postagemService;
    private final PostagemRepository postagemRepository;
    private PostagemDTO postagemDTO;

    public PostagemController(PostagemService postagemService, PostagemRepository postagemRepository) {
        this.postagemService = postagemService;
        this.postagemRepository = postagemRepository;
    }

    @GetMapping("/postagens")
    public ResponseEntity<List<PostagemDTO>> buscarPostagens(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer tagId,
            @RequestParam(defaultValue = "true") Boolean recentes,
            @RequestParam(required = false) String usernameSolicitante
    ) {
        Usuario solicitante = null;
        if (usernameSolicitante != null) {
            solicitante = new Usuario();
            solicitante.setUsername(usernameSolicitante);
        }
        List<PostagemDTO> resultado = postagemService
                .buscarPostagens(titulo, tagId, Boolean.TRUE.equals(recentes), solicitante)
                .stream()
                .map(PostagemDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/postagens/{id}")
    public ResponseEntity<PostagemDTO> acessarPostagem(
            @PathVariable Integer id,
            @RequestParam(required = false) String usernameSolicitante
    ) {
        Usuario solicitante = null;
        if (usernameSolicitante != null) {
            solicitante = new Usuario();
            solicitante.setUsername(usernameSolicitante);
        }
        Postagem postagem = postagemService.acessarPostagem(id, solicitante);
        return ResponseEntity.ok(PostagemDTO.fromEntity(postagem));
    }

    @PostMapping("/postagens")
    public ResponseEntity<PostagemDTO> criarPostagem(@Valid @RequestBody PostagemDTO dto) {
        Postagem entidade = dto.toEntity();
        Postagem criada = postagemService.criarPostagem(entidade, entidade.getAutor());
        return ResponseEntity.ok(PostagemDTO.fromEntity(criada));
    }

    @PutMapping("/postagens/{id}")
    public ResponseEntity<PostagemDTO> editarPostagem(
            @PathVariable Integer id,
            @Valid @RequestBody PostagemDTO dto
    ) {
        Postagem entidade = dto.toEntity();
        Postagem editada = postagemService.editarPostagem(id, entidade, entidade.getAutor());
        return ResponseEntity.ok(PostagemDTO.fromEntity(editada));
    }

    @DeleteMapping("/postagens/{id}")
    public ResponseEntity<Void> excluirPostagem(
            @PathVariable Integer id,
            @RequestParam String usernameSolicitante
    ) {
        Usuario solicitante = new Usuario();
        solicitante.setUsername(usernameSolicitante);
        postagemService.excluirPostagem(id, solicitante);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/postagens/{id}/moderar")
    public ResponseEntity<PostagemDTO> moderarPostagem(
            @PathVariable Integer id,
            @RequestParam String adminUsername
    ) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        Postagem moderada = postagemService.moderarPostagem(id, administrador);
        return ResponseEntity.ok(PostagemDTO.fromEntity(moderada));
    }
}

