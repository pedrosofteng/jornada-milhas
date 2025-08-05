package br.com.jornada.milhas.domain.destinos;

import br.com.jornada.milhas.domain.depoimentos.DepoimentosDetalhesDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable; // ✅ CORRETO


@Service
public class DestinoService {

    @Autowired
    private DestinoRepository repository;

    public ResponseEntity<?> mostrarDestinos(Pageable paginacao) {
        Page<DestinoDetalhesDTO> destinos = repository.findAllByAtivoTrue(paginacao).map(DestinoDetalhesDTO::new);
        return ResponseEntity.ok(destinos);
    }

    public ResponseEntity<?> postarDestino(@Valid DestinoCadastrarDTO dados, UriComponentsBuilder uriBuilder) {
        Destino destino = repository.save(new Destino(dados));

        var uri = uriBuilder.path("/destino/{id}").buildAndExpand(destino.getId()).toUri();
        return ResponseEntity.created(uri).body(new DestinoDetalhesDTO(destino));
    }

    public ResponseEntity<DestinoDetalhesDTO> alterarDestino(DestinoAtualizacaoDTO dados) {
        Destino destino = repository.findById(dados.id()).get();
        destino.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DestinoDetalhesDTO(destino));
    }

    public ResponseEntity<?> excluir(Long id) {
        var destino = repository.getReferenceById(id);
        destino.excluir();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> buscarDestinoEspecificoPorNome(String nome, Pageable paginacao) {
        Page<DestinoDetalhesDTO> destinos = repository
                .findAllByAtivoTrueAndNomeIgnoreCase(paginacao, nome)
                .map(DestinoDetalhesDTO::new);

        return ResponseEntity.ok(destinos);
    }

    public ResponseEntity<DestinoDetalhesDTO> mostrarDestinoEspecificoPorId(Long id) {
        var destino = repository.findById(id).get();
        return ResponseEntity.ok(new DestinoDetalhesDTO(destino));
    }
}
