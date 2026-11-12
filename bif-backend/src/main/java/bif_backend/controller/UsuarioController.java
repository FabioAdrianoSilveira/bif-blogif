package bif_backend.controller;

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

import bif_backend.dto.UsuarioDTO;
import bif_backend.model.Usuario;
import bif_backend.model.UsuarioRepository;
import bif_backend.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private UsuarioDTO usuarioDTO;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario salvo = usuarioService.cadastrarUsuario(dto.toEntity());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(salvo));
    }

    @PutMapping("/usuarios/{username}")
    public ResponseEntity<UsuarioDTO> atualizar(
            @PathVariable String username,
            @Valid @RequestBody UsuarioDTO dto
    ) {
        Usuario atualizado = usuarioService.atualizarConta(username, dto.toEntity());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(atualizado));
    }

    @DeleteMapping("/usuarios/{username}")
    public ResponseEntity<Void> excluir(@PathVariable String username) {
        usuarioService.excluirConta(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{username}")
    public ResponseEntity<UsuarioDTO> buscarPerfil(@PathVariable String username) {
        Usuario encontrado = usuarioService.buscarPorUsername(username);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(encontrado));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO credenciais) {
        Usuario autenticado = usuarioService.autenticar(credenciais.username(), credenciais.senha());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(autenticado));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        usuarioService.logout();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/usuarios/{username}/administrador")
    public ResponseEntity<UsuarioDTO> concederAdministrador(
            @PathVariable String username,
            @RequestParam String adminUsername
    ) {
        Usuario promovido = usuarioService.concederPrivilegiosAdministrador(adminUsername, username);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(promovido));
    }
}

