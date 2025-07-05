package br.com.jornada.milhas.domain.dto;

import br.com.jornada.milhas.domain.model.Depoimentos;
import jakarta.validation.constraints.NotBlank;

public record DepoimentosDTO(
        @NotBlank
        String fotoUrl,
        @NotBlank
        String depoimento,
        @NotBlank
        String nome
) {
        public DepoimentosDTO(Depoimentos depoimentos){
                this(depoimentos.getFotoUrl(), depoimentos.getDepoimento(), depoimentos.getNome());
        }
}
