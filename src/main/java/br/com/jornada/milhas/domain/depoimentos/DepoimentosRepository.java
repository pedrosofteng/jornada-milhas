package br.com.jornada.milhas.domain.depoimentos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepoimentosRepository extends JpaRepository<Depoimentos, Long> {
    Page<Depoimentos> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT * FROM depoimentos WHERE ativo = true ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Depoimentos>buscarTresAleatorios();
}
