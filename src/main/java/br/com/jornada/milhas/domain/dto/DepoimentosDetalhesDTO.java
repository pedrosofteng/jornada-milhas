package br.com.jornada.milhas.domain.dto;

import br.com.jornada.milhas.domain.model.Depoimentos;

public record DepoimentosDetalhesDTO(String depoimento,
                                     String nome) {
    public DepoimentosDetalhesDTO(Depoimentos depoimentos) {
        this(depoimentos.getDepoimento(), depoimentos.getNome());
    }
}
