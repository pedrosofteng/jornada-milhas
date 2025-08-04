package br.com.jornada.milhas.domain.destinos;

public record DestinoDetalhesDTO(
        String foto1,
        String foto2,
        String meta,
        String textoDescritivo,
        String nome,
        Double preco
) {
    public DestinoDetalhesDTO(Destino destino) {
        this(
                destino.getFoto1(),
                destino.getFoto2(),
                destino.getMeta(),
                destino.getTextoDescritivo(),
                destino.getNome(),
                destino.getPreco());
    }
}

