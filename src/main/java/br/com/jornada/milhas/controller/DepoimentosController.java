package br.com.jornada.milhas.controller;

import br.com.jornada.milhas.domain.dto.DepoimentosAtualizacaoDTO;
import br.com.jornada.milhas.domain.dto.DepoimentosDTO;
import br.com.jornada.milhas.domain.dto.DepoimentosDetalhesDTO;
import br.com.jornada.milhas.domain.model.Depoimentos;
import br.com.jornada.milhas.domain.service.DepoimentosService;
import br.com.jornada.milhas.repository.DepoimentosRepository;
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
    public ResponseEntity<DepoimentosDetalhesDTO> postarDepoimento(@RequestBody @Valid DepoimentosDTO depoimentosDTO,
                                                                   UriComponentsBuilder uriBuilder){
        return depoimentosService.guardarDepoimento(depoimentosDTO, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DepoimentosDTO>> mostrarDepoimentos
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
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return depoimentosService.excluirDados(id);
    }

    @GetMapping("/home")
    public ResponseEntity<List<DepoimentosDTO>> depoimentosSorteados() {
        return depoimentosService.buscarTresAleatorios();
    }

}
