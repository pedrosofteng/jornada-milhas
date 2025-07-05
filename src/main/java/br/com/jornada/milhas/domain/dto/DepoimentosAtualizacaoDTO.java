package br.com.jornada.milhas.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DepoimentosAtualizacaoDTO(

        Long id,

        String fotoUrl,

        String depoimento,

        String nome,

        Boolean ativo) {
}
