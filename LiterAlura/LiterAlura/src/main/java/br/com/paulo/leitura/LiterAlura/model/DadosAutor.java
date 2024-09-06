package br.com.paulo.leitura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record DadosAutor(Integer id,
                         @JsonAlias("name") String autor,
                         @JsonAlias("birth_year") Year periodoIni,
                         @JsonAlias("death_year") Year periodoFim,
                         @JsonAlias("title") String titulo)
 {
}
