package bif_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bif_backend.dto.ChamadoDTO;
import bif_backend.model.Chamado;
import bif_backend.model.ChamadoRepository;
import bif_backend.model.Usuario;
import bif_backend.service.ChamadoService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class ChamadoController {

    private final ChamadoService chamadoService;
    private final ChamadoRepository chamadoRepository;
    private ChamadoDTO chamadoDTO;

    public ChamadoController(ChamadoService chamadoService, ChamadoRepository chamadoRepository) {
        this.chamadoService = chamadoService;
        this.chamadoRepository = chamadoRepository;
    }

    @PostMapping("/chamados")
    public ResponseEntity<ChamadoDTO> abrirChamado(@Valid @RequestBody ChamadoDTO dto) {
        Chamado entidade = dto.toEntity();
        Chamado criado = chamadoService.abrirChamado(entidade, entidade.getRemetente());
        return ResponseEntity.ok(ChamadoDTO.fromEntity(criado));
    }

    @GetMapping("/chamados/meus")
    public ResponseEntity<List<ChamadoDTO>> listarMeusChamados(@RequestParam String remetenteUsername) {
        Usuario remetente = new Usuario();
        remetente.setUsername(remetenteUsername);
        List<ChamadoDTO> chamados = chamadoService.listarMeusChamados(remetente)
                .stream()
                .map(ChamadoDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/chamados")
    public ResponseEntity<List<ChamadoDTO>> listarTodos(@RequestParam String adminUsername) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        List<ChamadoDTO> chamados = chamadoService.listarTodosChamados(administrador)
                .stream()
                .map(ChamadoDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(chamados);
    }

    @PatchMapping("/chamados/{id}/responder")
    public ResponseEntity<ChamadoDTO> responderChamado(
            @PathVariable Integer id,
            @RequestBody ChamadoDTO dto,
            @RequestParam String adminUsername
    ) {
        Usuario administrador = new Usuario();
        administrador.setUsername(adminUsername);
        Chamado respondido = chamadoService.responderChamado(id, dto.respostaAdministrador(), administrador);
        return ResponseEntity.ok(ChamadoDTO.fromEntity(respondido));
    }
}

