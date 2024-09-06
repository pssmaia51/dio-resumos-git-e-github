package br.com.paulo.forumpaulo.domain.curso;

public record DadosDetalhamentoCurso(String curso, String categoria) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }
}
