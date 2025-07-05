package br.com.jornada.milhas.domain.model;

import br.com.jornada.milhas.domain.dto.DepoimentosAtualizacaoDTO;
import br.com.jornada.milhas.domain.dto.DepoimentosDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "depoimentos")
@EqualsAndHashCode(of = "id")
public class Depoimentos {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String fotoUrl;
    @Getter
    private String depoimento;
    @Getter
    private String nome;
    private Boolean ativo;

    public Depoimentos(){
    }

    public Depoimentos(DepoimentosDTO depoimentosDTO){
        this.fotoUrl = depoimentosDTO.fotoUrl();
        this.depoimento = depoimentosDTO.depoimento();
        this.nome = depoimentosDTO.nome();
    }

    public void atualizarInformacoes(@Valid DepoimentosAtualizacaoDTO dados) {
        if(dados.fotoUrl() != null) {
            this.fotoUrl = dados.fotoUrl();
        }

        if(dados.depoimento() != null) {
            this.depoimento = dados.depoimento();
        }

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.ativo() != null) {
            this.ativo = dados.ativo();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
