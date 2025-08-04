package br.com.jornada.milhas.domain.destinos;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable; // ✅ CORRETO

import java.util.Optional;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    Page<Destino> findAllByAtivoTrue(Pageable paginacao);

    Page<Destino> findAllByAtivoTrueAndNomeIgnoreCase(Pageable paginacao, String nome);
}
