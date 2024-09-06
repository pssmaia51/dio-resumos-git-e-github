package br.com.paulo.forumpaulo.domain.resposta;

import br.com.paulo.forumpaulo.domain.topicos.Topicos;
import br.com.paulo.forumpaulo.domain.usuarios.Usuarios;
import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id, String mensagem, Topicos topico, LocalDateTime dataCriacao, Usuarios autor, Boolean solucao) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico(), resposta.getDataCriacao(), resposta.getAutor(), resposta.getSolucao());
    }
}
