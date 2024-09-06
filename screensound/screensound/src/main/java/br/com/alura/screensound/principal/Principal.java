package br.com.alura.screensound.principal;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.model.TipoArtista;
import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.service.ConsultaChatGpt;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

            var opcao = -1;

            while (opcao!= 0) {
                var menu = """
                    ---------------------------------------------
                        Menu de Opções Screen Sound Músicas                    
                    ---------------------------------------------                     
                    1- Cadastrar artistas                      
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                    ---------------------------------------------                
                    0 - Sair
                    ---------------------------------------------
                    """;

                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarArtistas();
                        break;
                    case 2:
                        cadastrarMusicas();
                        break;
                    case 3:
                        listarMusicas();
                        break;
                    case 4:
                        buscarMusicasPorArtista();
                        break;
                    case 5:
                        pesquisarDadosDoArtista();
                        break;
                    case 0:
                        System.out.println("Encerrando a aplicação!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }


    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar dados sobre qual artista?");
        var nome = leitura.nextLine();
        var resposta = ConsultaChatGpt.obterInformacao(nome);
        System.out.println(resposta.trim());

    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musicas de que artista?");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);

    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void cadastrarMusicas() {

        System.out.println("Cadastrar musica de que artista?");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()){
            System.out.println("Informe titulo da musica");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        }else{
            System.out.println("Artista não encontrado");
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla ou banda)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
