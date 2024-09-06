package br.com.paulo.forumpaulo.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        Boolean ativo,
        String nome,
        String senha,
        String login,
        String email
) {
}
