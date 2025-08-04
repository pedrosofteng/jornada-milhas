package br.com.jornada.milhas.domain.depoimentos;

import jakarta.validation.constraints.NotBlank;

public record DepoimentosCadastrarDTO(
        @NotBlank
        String fotoUrl,
        @NotBlank
        String depoimento,
        @NotBlank
        String nome
) {
        public DepoimentosCadastrarDTO(Depoimentos depoimentos){
                this(depoimentos.getFotoUrl(), depoimentos.getDepoimento(), depoimentos.getNome());
        }
}
