package bif_backend.dto;

import bif_backend.model.Tag;

public record TagDTO(
        Integer id,
        String nome
) {

    public static TagDTO fromEntity(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagDTO(
                tag.getId(),
                tag.getNome()
        );
    }

    public Tag toEntity() {
        return new Tag(
                this.id,
                this.nome
        );
    }
}

