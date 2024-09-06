package br.com.paulo.forumpaulo.controller;

import br.com.paulo.forumpaulo.domain.resposta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        var resposta = new Resposta(dados);

        repository.save(new Resposta(dados));

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoResposta>> listar(@PageableDefault(size = 10, sort= {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoResposta::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoResposta dados) {
        var resposta = repository.getReferenceById(dados.id());
        resposta.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var resposta = repository.getReferenceById(id);
        resposta.excluir();

        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var resposta = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

}
