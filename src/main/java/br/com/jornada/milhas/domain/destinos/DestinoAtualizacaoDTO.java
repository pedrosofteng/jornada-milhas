package br.com.jornada.milhas.domain.destinos;

import jakarta.validation.constraints.NotNull;

public record DestinoAtualizacaoDTO(
        @NotNull
        Long id,
        String foto1,
        String foto2,
        String meta,
        String textoDescritivo,
        String nome,
        Double preco
) {
}
