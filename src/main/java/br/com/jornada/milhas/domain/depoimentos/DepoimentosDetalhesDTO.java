package br.com.jornada.milhas.domain.depoimentos;

public record DepoimentosDetalhesDTO(String depoimento,
                                     String nome) {
    public DepoimentosDetalhesDTO(Depoimentos depoimentos) {
        this(depoimentos.getDepoimento(), depoimentos.getNome());
    }
}
