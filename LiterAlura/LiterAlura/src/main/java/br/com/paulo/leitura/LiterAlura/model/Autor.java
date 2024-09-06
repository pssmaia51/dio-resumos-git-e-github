package br.com.paulo.leitura.LiterAlura.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor;
    private Year periodoIni;
    private Year periodoFim;
    private String titulo;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    List<Livro> livros = new ArrayList<>();

    public Autor(Autor autor){}

    public Autor(DadosAutor dadosAutor) {
        this.autor = dadosAutor.autor();
        this.periodoIni = (dadosAutor.periodoIni());
        this.periodoFim = (dadosAutor.periodoFim());
    }

    public Autor(String autor, Year periodoIni, Year periodoFim) {
        this.autor = autor;
        this.periodoIni = periodoIni;
        this.periodoFim = periodoFim;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Year getPeriodoIni() {
        return periodoIni;
    }

    public void setPeriodoIni(Year periodoIni) {
        this.periodoIni = periodoIni;
    }

    public Year getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(Year periodoFim) {
        this.periodoFim = periodoFim;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return    "Autor:           " + autor +
                "Ano Nascimento:  " + periodoIni +
                "Ano Falecimento: " + periodoFim +
                "Livros:          " + livros.stream().map(l -> l.getTitulo()).collect(Collectors.toList());
    }
}


