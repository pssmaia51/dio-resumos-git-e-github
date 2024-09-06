package br.com.paulo.leitura.LiterAlura.principal;

import br.com.paulo.leitura.LiterAlura.LivroRepository;
import br.com.paulo.leitura.LiterAlura.model.*;
import br.com.paulo.leitura.LiterAlura.service.ConsumoApi;
import br.com.paulo.leitura.LiterAlura.service.ConverteDados;

import java.time.Year;
import java.util.*;

public class Principal {

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);
    private LivroRepository repositorio;
    private String endereco = "https://gutendex.com/books/?search=";

    public Principal(LivroRepository repository) {

        this.repositorio = repository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    *** Menu de Opções ***
                    1- Buscar Livro Pelo Titulo
                    2- Lista Livros Registrados
                    3- Lista Autores Registrados
                    4- Listar Autores Vivos em um Determinado Ano
                    5- Listar Livro em Determinado Idioma
                    6- Estatísticas dos livros
                                                        
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listaLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresAno();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 6:
                    estatisticaLivro();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    leitura.nextLine();
            }
        }
    }

        private void buscarLivroPeloTitulo() {
        System.out.println("Digite nome do livro que deseja buscar: ");
        var nomeLivro = leitura.nextLine();
        System.out.println("Buscando livro...");
        String enderecoBusca = endereco.concat(nomeLivro.replace(" ", "+").toLowerCase().trim());

        String json = ConsumoApi.obterDados(enderecoBusca);
        String jsonLivro = converte.extraiObjetoJson(json, "results");

        List<DadosLivros> livrosDTO = converte.obterLista(jsonLivro, DadosLivros.class);

        if (livrosDTO.size() > 0) {
            Livro livro = new Livro(livrosDTO.get(0));


            Autor autor = repositorio.buscarAutorPeloNome(livro.getAutor());
            if (autor != null) {
                livro.setAutor(null);
                repositorio.save(livro);
                livro.setAutor(String.valueOf(autor));
            }
            livro = repositorio.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listaLivros() {
        List<Livro> livros = repositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = repositorio.buscarAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresAno() {
        try {
            System.out.println("Digite o ano:");
            int ano = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autores = repositorio.buscarAutoresVivosNoAno(Year.of(ano));
            autores.forEach(System.out::println);
        }catch (InputMismatchException e){
            System.out.println("Ano invalido. Digite um ano valido.");
            leitura.nextLine();
        }
    }

    private void listarLivrosIdioma() {
                System.out.println("""
                Digite abreveatura do idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
            String idioma = leitura.nextLine();
            List<Livro> livros = repositorio.findByIdioma(idioma);
            if (!livros.isEmpty()){
                livros.forEach(System.out::println);
            }else{
                System.out.println("Não exite livros nesse idioma cadastrado");
            }
    }

    private void estatisticaLivro() {
        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();

        List<Double> dadosDaConsulta = repositorio.buscaNDownload();
        for (Double valor : dadosDaConsulta){
            statistics.accept(valor);
        }

        System.out.println("Estatísticas de Downloads dos Livros:");
        System.out.println("Média: " + statistics.getAverage());
        System.out.println("Mínimo: " + statistics.getMin());
        System.out.println("Máximo: " + statistics.getMax());
        System.out.println("Soma: " + statistics.getSum());
        System.out.println("Contagem: " + statistics.getCount());
    }
    }