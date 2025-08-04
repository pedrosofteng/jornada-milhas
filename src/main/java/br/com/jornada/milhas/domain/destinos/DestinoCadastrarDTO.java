package br.com.jornada.milhas.domain.destinos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DestinoCadastrarDTO(
        @NotBlank
        String foto1,
        @NotBlank
        String foto2,
        @NotBlank
        String nome,
        @NotNull
        Double preco,
        @NotBlank
        String meta,
        String textoDescritivo

) {
}
