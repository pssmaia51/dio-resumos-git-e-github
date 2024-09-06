package br.com.paulo.forumpaulo.domain.curso;

public record DadosAtualizacaoCurso(
        Long id,
        Boolean ativo,
        String nome,
        String categoria
) {
}
