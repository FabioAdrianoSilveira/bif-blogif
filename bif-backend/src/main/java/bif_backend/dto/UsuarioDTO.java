package bif_backend.dto;

import bif_backend.model.Usuario;

public record UsuarioDTO(
        String username,
        String email,
        String senha,
        String nome,
        Boolean administrador
) {

    public static UsuarioDTO fromEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getAdministrador()
        );
    }

    public Usuario toEntity() {
        return new Usuario(
                this.username,
                this.email,
                this.senha,
                this.nome,
                this.administrador
        );
    }
}

