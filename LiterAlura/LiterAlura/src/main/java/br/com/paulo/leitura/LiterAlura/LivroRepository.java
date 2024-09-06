package br.com.paulo.leitura.LiterAlura;

import br.com.paulo.leitura.LiterAlura.model.Autor;
import br.com.paulo.leitura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdioma(String idioma);

    @Query("SELECT l.numeroDownload FROM Livro l")
    List<Double> buscaNDownload();

    @Query("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> buscarAutores();

    @Query ("SELECT a FROM Livro l JOIN l.autor a WHERE a.periodoIni <= :ano and a.periodoFim >= :ano")
    List<Autor> buscarAutoresVivosNoAno(Year ano);

    @Query ("SELECT a FROM Livro l JOIN l.autor a WHERE a.autor = :autor")
    Autor buscarAutorPeloNome(String autor);
}
