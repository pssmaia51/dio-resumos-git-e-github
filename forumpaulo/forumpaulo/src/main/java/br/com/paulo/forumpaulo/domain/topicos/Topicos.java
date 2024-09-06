package br.com.paulo.forumpaulo.domain.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "medicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String messagem;
    private LocalDate dataCriacao;
    private String status;
    private String autor;
    private String curso;
    private String resposta;

    private Boolean ativo;


    public Topicos(CadastroTopico dados) {
        this.titulo = dados.titulo();
        this.messagem = dados.messagem();
        this.dataCriacao = dados.dataCriacao();
        this.status = dados.status();
        this.autor = dados.autor();
        this.curso = dados.curso();
        this.resposta = dados.resposta();

    }

    public void atualizarInformacoes(AtualizacaoTopicos dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.messagem() != null) {
            this.messagem = dados.messagem();
        }
        if (dados.dataCriacao() != null) {
            this.dataCriacao = dados.dataCriacao();
        }
    }

        public void desativar() {
            this.ativo = false;
        }
    }
