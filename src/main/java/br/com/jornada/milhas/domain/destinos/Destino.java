package br.com.jornada.milhas.domain.destinos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Destino")
@Table(name = "destino")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foto1;

    private String foto2;

    private String nome;

    private Double preco;

    private String textoDescritivo;

    private String meta;

    private String mensagemErro;

    private Boolean ativo = true;

    public Destino(@Valid DestinoCadastrarDTO dados) {
        this.foto1 = dados.foto1();
        this.foto2 = dados.foto2();
        this.meta = dados.meta();
        this.textoDescritivo = dados.textoDescritivo();
        this.nome = dados.nome();
        this.preco = dados.preco();
    }

    public void atualizarInformacoes(DestinoAtualizacaoDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.foto1() != null) {
            this.foto1 = dados.foto1();
        }

        if(dados.foto2() != null) {
            this.foto2 = dados.foto2();
        }

        if(dados.meta() != null)  {
            this.meta = dados.meta();
        }

        if(dados.textoDescritivo() != null) {
            this.textoDescritivo = dados.textoDescritivo();
        }

        if(dados.preco() != null) {
            this.preco = dados.preco();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
