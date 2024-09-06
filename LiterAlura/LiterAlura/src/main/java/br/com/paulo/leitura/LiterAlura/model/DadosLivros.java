package br.com.paulo.leitura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(Integer id,
                          @JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<Autor> autor,
                          @JsonAlias("languages") String idioma,
                          @JsonAlias("download_count") String ndonwload)

                           {
}



