package br.com.paulo.forumpaulo.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroTopico(
    @NotBlank
    String titulo,
    @NotBlank
    String messagem,
    @NotBlank
    LocalDate dataCriacao,
    @NotBlank
    String status,
    @NotBlank
    String autor,
    @NotNull
    String curso,
    @NotNull
    String resposta) {
}
