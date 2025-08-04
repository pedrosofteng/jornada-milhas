package br.com.jornada.milhas.controller;

import br.com.jornada.milhas.domain.depoimentos.DepoimentosAtualizacaoDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosCadastrarDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosDetalhesDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosService;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentosController {

    @Autowired
    private DepoimentosRepository repository;

    @Autowired
    private DepoimentosService depoimentosService;

    @PostMapping
    @Transactional
    public ResponseEntity<DepoimentosDetalhesDTO> postarDepoimento(
            @RequestBody
            @Valid
            DepoimentosCadastrarDTO depoimentosCadastrarDTO,
            UriComponentsBuilder uriBuilder){
        return depoimentosService.guardarDepoimento(depoimentosCadastrarDTO, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DepoimentosCadastrarDTO>> mostrarDepoimentos
            (@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
        return depoimentosService.mostrarDepoimento(pageable);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DepoimentosDetalhesDTO> atualizar(@RequestBody @Valid DepoimentosAtualizacaoDTO dados) {
        return depoimentosService.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return depoimentosService.excluirDados(id);
    }

    @GetMapping("/home")
    public ResponseEntity<List<DepoimentosCadastrarDTO>> depoimentosSorteados() {
        return depoimentosService.buscarTresAleatorios();
    }

}
