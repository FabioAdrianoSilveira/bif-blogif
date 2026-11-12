package bif_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bif_backend.model.Tag;
import bif_backend.model.TagRepository;
import bif_backend.model.Usuario;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private Tag tag;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag cadastrarTag(Tag tag, Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador)
        return tagRepository.save(tag);
    }

    public Tag editarTag(Integer id, Tag dados, Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador; atualizar nome da tag)
        return tagRepository.save(dados);
    }

    public void excluirTag(Integer id, Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador)
        tagRepository.deleteById(id);
    }

    public List<Tag> listarTags() {
        // TODO: Implementar regras de negócio
        return tagRepository.findAll();
    }
}

