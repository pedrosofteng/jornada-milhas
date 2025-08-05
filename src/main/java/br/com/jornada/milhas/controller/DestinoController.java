package br.com.jornada.milhas.controller;

import br.com.jornada.milhas.domain.destinos.DestinoAtualizacaoDTO;
import br.com.jornada.milhas.domain.destinos.DestinoCadastrarDTO;
import br.com.jornada.milhas.domain.destinos.DestinoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable; // ✅ CORRETO


@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService service;

    @GetMapping
    public ResponseEntity<?> verDestinos(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return service.mostrarDestinos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDestinoEspecificoPorId(@PathVariable Long id) {
        return service.mostrarDestinoEspecificoPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> postarDestino(
            @RequestBody
            @Valid
            DestinoCadastrarDTO dados,
            UriComponentsBuilder uriBuilder) {
        return service.postarDestino(dados, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> alterarDestino(@RequestBody DestinoAtualizacaoDTO dados) {
        return service.alterarDestino(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return service.excluir(id);
    }

    @GetMapping("?nome={nome}")
    public ResponseEntity<?> mostrarDestinoEspecificoPorNome(
            @PathVariable
            String nome,
            @PageableDefault(size = 10, page = 0, sort = {"nome"})
            Pageable paginacao) {
        return service.buscarDestinoEspecificoPorNome(nome, paginacao);
    }
}
