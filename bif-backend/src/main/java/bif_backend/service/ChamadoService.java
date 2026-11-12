package bif_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bif_backend.model.Chamado;
import bif_backend.model.ChamadoRepository;
import bif_backend.model.Usuario;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private Chamado chamado;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado abrirChamado(Chamado chamado, Usuario remetente) {
        // TODO: Implementar regras de negócio (definir remetente, dataDeCriacao = LocalDate.now(), atendido = false)
        return chamadoRepository.save(chamado);
    }

    public List<Chamado> listarMeusChamados(Usuario remetente) {
        // TODO: Implementar regras de negócio (listar chamados do remetente)
        String username = remetente != null ? remetente.getUsername() : null;
        return chamadoRepository.findByRemetenteUsernameOrderByDataDeCriacaoDesc(username);
    }

    public List<Chamado> listarTodosChamados(Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador)
        return chamadoRepository.findAllByOrderByDataDeCriacaoDesc();
    }

    public Chamado responderChamado(Integer chamadoId, String respostaAdministrador, Usuario administrador) {
        // TODO: Implementar regras de negócio (exclusivo para Administrador; registrar resposta e atendido = true)
        return chamadoRepository.findById(chamadoId).orElse(null);
    }
}

