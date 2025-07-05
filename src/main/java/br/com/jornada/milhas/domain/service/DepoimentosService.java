package br.com.jornada.milhas.domain.service;

import br.com.jornada.milhas.domain.dto.DepoimentosAtualizacaoDTO;
import br.com.jornada.milhas.domain.dto.DepoimentosDTO;
import br.com.jornada.milhas.domain.dto.DepoimentosDetalhesDTO;
import br.com.jornada.milhas.domain.model.Depoimentos;
import br.com.jornada.milhas.repository.DepoimentosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DepoimentosService {

    @Autowired
    private DepoimentosRepository repository;

    public ResponseEntity<DepoimentosDetalhesDTO> guardarDepoimento(@Valid DepoimentosDTO depoimentosDTO,
                                                                    UriComponentsBuilder uriBuilder) {
        Depoimentos depoimentos = new Depoimentos(depoimentosDTO);
        repository.save(depoimentos);

        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimentos.getId()).toUri();
        return ResponseEntity.created(uri).body(new DepoimentosDetalhesDTO(depoimentos));
    }

    public ResponseEntity<Page<DepoimentosDTO>> mostrarDepoimento(Pageable paginacao) {
        Page<DepoimentosDTO> page = converteDados(paginacao);
        return ResponseEntity.ok(page);
    }

    public Page<DepoimentosDTO> converteDados(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DepoimentosDTO::new);
    }

    public ResponseEntity<DepoimentosDetalhesDTO> atualizarDados(@Valid DepoimentosAtualizacaoDTO dados) {
        var depoimento = repository.getReferenceById(dados.id());
        depoimento.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DepoimentosDetalhesDTO(depoimento));
    }

    public ResponseEntity<Void> excluirDados(Long id) {
        var depoimentos = repository.getReferenceById(id);
        depoimentos.excluir();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<DepoimentosDTO>> buscarTresAleatorios() {
        var depoimentos =repository.buscarTresAleatorios()
                .stream()
                .map(depoimento -> new DepoimentosDTO(depoimento))
                .toList();
        return ResponseEntity.ok(depoimentos);
    }
}
