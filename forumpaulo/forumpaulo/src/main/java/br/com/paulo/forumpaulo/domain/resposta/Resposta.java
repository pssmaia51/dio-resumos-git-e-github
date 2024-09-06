package br.com.paulo.forumpaulo.domain.resposta;

import br.com.paulo.forumpaulo.domain.topicos.Topicos;
import br.com.paulo.forumpaulo.domain.usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String mensagem;
    @ManyToOne(fetch = FetchType.LAZY)
    private Topicos topico;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios autor;
    private Boolean solucao = false;

    public Resposta(DadosCadastroResposta dados) {
        this.ativo = true;
        this.mensagem = dados.mensagem();
        this.topico = dados.topico();
        this.dataCriacao = dados.dataCriacao();
        this.autor = dados.autor();
        this.solucao = dados.solucao();
    }
    public void atualizarInformacoes(DadosAtualizacaoResposta dados) {
        if (dados.id() != null) {
            this.id = dados.id();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.topico() != null) {
            this.topico = dados.topico();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.solucao() != null) {
            this.solucao = dados.solucao();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
