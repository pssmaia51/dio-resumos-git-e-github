package br.com.paulo.forumpaulo.domain.resposta;

import br.com.paulo.forumpaulo.domain.topicos.Topicos;
import br.com.paulo.forumpaulo.domain.usuarios.Usuarios;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        @NotNull
        Long id,
        String mensagem,
        Topicos topico,
        Usuarios autor,
        Boolean solucao
) {
}
