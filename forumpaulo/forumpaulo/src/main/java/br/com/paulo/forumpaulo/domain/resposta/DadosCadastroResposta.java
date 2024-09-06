package br.com.paulo.forumpaulo.domain.resposta;

import br.com.paulo.forumpaulo.domain.topicos.Topicos;
import br.com.paulo.forumpaulo.domain.usuarios.Usuarios;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem,
        @NotNull
        @Valid
        Topicos topico,
        @NotBlank
        LocalDateTime dataCriacao,
        @NotNull
        @Valid
        Usuarios autor,
        @NotBlank
        Boolean solucao) {
}
