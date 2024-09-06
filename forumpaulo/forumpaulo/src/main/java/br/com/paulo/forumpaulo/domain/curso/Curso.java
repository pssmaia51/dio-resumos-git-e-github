package br.com.paulo.forumpaulo.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    @Column(name = "nome_curso")
    private String nome;
    @Column(name = "categoria_curso")
    private String categoria;

    public Curso(DadosCadastroCurso dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
    public void atualizarInformacoes(DadosAtualizacaoCurso dados) {
        if (dados.id() != null) {
            this.id = dados.id();
        }
        if (dados.ativo() != null) {
            this.ativo = dados.ativo();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
