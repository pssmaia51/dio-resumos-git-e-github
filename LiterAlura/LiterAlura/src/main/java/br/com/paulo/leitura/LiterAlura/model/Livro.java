package br.com.paulo.leitura.LiterAlura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private String autor;
    private String idioma;
    private Double ndonwload;

    public Livro(){}

    public Livro(DadosLivros dadosLivros) {
        this.titulo = dadosLivros.titulo();
        Autor autor = new Autor(dadosLivros.autor().get(0));
        this.autor = autor.toString();
        //this.idioma = dadosLivros.idioma().getChars(0);
        this.ndonwload = Double.valueOf(dadosLivros.ndonwload());
    }

    public Livro(Long idApi, String titulo, Autor autor, String idioma, Double nDownload) {
        this.titulo = titulo;
        this.autor = autor.toString();
        this.idioma = idioma;
        this.ndonwload = nDownload;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNdonwload() {
        return ndonwload;
    }

    public void setNdonwload(Double ndonwload) {
        this.ndonwload = ndonwload;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", ndonwload=" + ndonwload +
                '}';
    }
}
