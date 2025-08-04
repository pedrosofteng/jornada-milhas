package br.com.jornada.milhas.domain.depoimentos;

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

    public ResponseEntity<DepoimentosDetalhesDTO> guardarDepoimento(@Valid DepoimentosCadastrarDTO depoimentosCadastrarDTO,
                                                                    UriComponentsBuilder uriBuilder) {
        Depoimentos depoimentos = new Depoimentos(depoimentosCadastrarDTO);
        repository.save(depoimentos);

        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimentos.getId()).toUri();
        return ResponseEntity.created(uri).body(new DepoimentosDetalhesDTO(depoimentos));
    }

    public ResponseEntity<Page<DepoimentosCadastrarDTO>> mostrarDepoimento(Pageable paginacao) {
        Page<DepoimentosCadastrarDTO> page = converteDados(paginacao);
        return ResponseEntity.ok(page);
    }

    public Page<DepoimentosCadastrarDTO> converteDados(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DepoimentosCadastrarDTO::new);
    }

    public ResponseEntity<DepoimentosDetalhesDTO> atualizarDados(@Valid DepoimentosAtualizacaoDTO dados) {
        var depoimento = repository.getReferenceById(dados.id());
        depoimento.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DepoimentosDetalhesDTO(depoimento));
    }

    public ResponseEntity<?> excluirDados(Long id) {
        var depoimentos = repository.getReferenceById(id);
        depoimentos.excluir();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<DepoimentosCadastrarDTO>> buscarTresAleatorios() {
        var depoimentos = repository.buscarTresAleatorios()
                .stream()
                .map(DepoimentosCadastrarDTO::new)
                .toList();
        return ResponseEntity.ok(depoimentos);
    }
}
