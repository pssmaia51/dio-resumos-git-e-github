package br.com.paulo.forumpaulo.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
        @NotBlank
        String nome,
        @NotBlank
        String categoria) {
}
