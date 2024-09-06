package br.com.paulo.forumpaulo.controller;

import br.com.paulo.forumpaulo.domain.topicos.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

   //Cadastro de Topicos
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroTopico dados, UriComponentsBuilder UriBuilder) {
        var topico = new Topicos(dados);
        repository.save(topico);

        var uri = UriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopícos(topico));
    }
    //Listagem de Topicos
    @GetMapping
    public ResponseEntity <Page<DadosListagemTopicos>> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemTopicos::new);
        return ResponseEntity.ok(page);
    }
    //Editar Topicos
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoTopicos dados) {
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopícos(topico));

    }
//Desativar Topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity ativo(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();

        return ResponseEntity.noContent().build();

    }
//Detalhar Topico
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopícos(topico));
    }
}

