package br.com.paulo.forumpaulo.domain.topicos;

import java.time.LocalDate;

public record DadosDetalhamentoTopícos(
        Long id,
        String titulo,
        String messagem,
        LocalDate dataCriacao,
        String topicosStatus, String status,
        String autor,
        String curso
) {
    public DadosDetalhamentoTopícos(Topicos topicos) {
        this(topicos.getId(),
                topicos.getTitulo(),
                topicos.getMessagem(),
                topicos.getDataCriacao(),
                topicos.getStatus(),
                topicos.getAutor(),
                topicos.getCurso(),
                topicos.getResposta());

    }
}
