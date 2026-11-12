package bif_backend.dto;

import java.time.LocalDate;

import bif_backend.model.Assunto;
import bif_backend.model.Chamado;
import bif_backend.model.Usuario;

public record ChamadoDTO(
        Integer id,
        String conteudo,
        LocalDate dataDeCriacao,
        Boolean atendido,
        String respostaAdministrador,
        String remetenteUsername,
        Assunto assunto
) {

    public static ChamadoDTO fromEntity(Chamado chamado) {
        if (chamado == null) {
            return null;
        }
        return new ChamadoDTO(
                chamado.getId(),
                chamado.getConteudo(),
                chamado.getDataDeCriacao(),
                chamado.getAtendido(),
                chamado.getRespostaAdministrador(),
                chamado.getRemetente() != null ? chamado.getRemetente().getUsername() : null,
                chamado.getAssunto()
        );
    }

    public Chamado toEntity() {
        Chamado chamado = new Chamado();
        chamado.setId(this.id);
        chamado.setConteudo(this.conteudo);
        chamado.setDataDeCriacao(this.dataDeCriacao);
        chamado.setAtendido(this.atendido);
        chamado.setRespostaAdministrador(this.respostaAdministrador);
        if (this.remetenteUsername != null) {
            Usuario remetente = new Usuario();
            remetente.setUsername(this.remetenteUsername);
            chamado.setRemetente(remetente);
        }
        chamado.setAssunto(this.assunto);
        return chamado;
    }
}

