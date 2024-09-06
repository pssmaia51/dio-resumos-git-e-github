package br.com.paulo.forumpaulo.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizacaoTopicos(
    @NotNull
    Long id,
    String titulo,
    String messagem,
    LocalDate dataCriacao) {

}
