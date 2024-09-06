package br.com.paulo.forumpaulo.domain.usuarios;

public record DadosDetalhamentoUsuario(Long id, Boolean ativo, String nome, String login) {

    public DadosDetalhamentoUsuario(Usuarios usuario) {
        this(usuario.getId(), usuario.getAtivo(), usuario.getNome(), usuario.getLogin());
    }
}
