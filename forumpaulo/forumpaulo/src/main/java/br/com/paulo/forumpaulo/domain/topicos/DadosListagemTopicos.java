package br.com.paulo.forumpaulo.domain.topicos;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String status,
        String autor
)
{
    public DadosListagemTopicos(Topicos topicos){
        this(topicos.getId(), topicos.getTitulo(), topicos.getStatus(), topicos.getAutor());

    }


}
