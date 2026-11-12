package bif_backend.service;

import org.springframework.stereotype.Service;

import bif_backend.model.Usuario;
import bif_backend.model.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private Usuario usuario;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        // TODO: Implementar regras de negócio (validar unicidade de username/email, definir administrador = false)
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarConta(String username, Usuario dadosAtualizados) {
        // TODO: Implementar regras de negócio (atualizar nome, email, senha do próprio usuário)
        return usuarioRepository.save(dadosAtualizados);
    }

    public void excluirConta(String username) {
        // TODO: Implementar regras de negócio
        usuarioRepository.deleteById(username);
    }

    public Usuario autenticar(String username, String senha) {
        // TODO: Implementar regras de negócio (validar credenciais para iniciar sessão)
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    public void logout() {
        // TODO: Implementar regras de negócio (encerrar sessão ativa do usuário)
    }

    public Usuario concederPrivilegiosAdministrador(String adminUsername, String targetUsername) {
        // TODO: Implementar regras de negócio (validar se adminUsername é administrador e promover targetUsername)
        return usuarioRepository.findByUsername(targetUsername).orElse(null);
    }

    public Usuario buscarPorUsername(String username) {
        // TODO: Implementar regras de negócio
        return usuarioRepository.findByUsername(username).orElse(null);
    }
}

