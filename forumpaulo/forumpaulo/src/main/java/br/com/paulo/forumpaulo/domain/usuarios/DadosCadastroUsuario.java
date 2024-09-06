package br.com.paulo.forumpaulo.domain.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String login,
        String perfil)
{
}
