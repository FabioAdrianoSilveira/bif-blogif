package bif_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bif_backend.dto.TagDTO;
import bif_backend.model.Tag;
import bif_backend.model.TagRepository;
import bif_backend.model.Usuario;
import bif_backend.service.TagService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class TagController {

    private final TagService tagService;
    private final TagRepository tagRepository;
    private TagDTO tagDTO;

    public TagController(TagService tagService, TagRepository tagRepository) {
        this.tagService = tagService;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> listar() {
        List<TagDTO> tags = tagService.listarTags()
                .stream()
                .map(TagDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(tags);
    }

    @PostMapping("/tags")
    public ResponseEntity<TagDTO> cadastrar(
            @Valid @RequestBody TagDTO dto,
            @RequestParam String adminUsername
    ) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        Tag criada = tagService.cadastrarTag(dto.toEntity(), administrador);
        return ResponseEntity.ok(TagDTO.fromEntity(criada));
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<TagDTO> editar(
            @PathVariable Integer id,
            @Valid @RequestBody TagDTO dto,
            @RequestParam String adminUsername
    ) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        Tag editada = tagService.editarTag(id, dto.toEntity(), administrador);
        return ResponseEntity.ok(TagDTO.fromEntity(editada));
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            @RequestParam String adminUsername
    ) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        tagService.excluirTag(id, administrador);
        return ResponseEntity.noContent().build();
    }
}

