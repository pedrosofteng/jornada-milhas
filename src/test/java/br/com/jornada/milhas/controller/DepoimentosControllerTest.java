package br.com.jornada.milhas.controller;

import br.com.jornada.milhas.config.ConfigEnv;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosAtualizacaoDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosCadastrarDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosDetalhesDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DepoimentosControllerTest {

    @BeforeAll
    static void setupEnv() {
        ConfigEnv configEnv = new ConfigEnv();
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DepoimentosAtualizacaoDTO> depoimentosAtualizacaoDTOJacksonTester;

    @Autowired
    private JacksonTester<DepoimentosCadastrarDTO> depoimentosCadastrarDTOJacksonTester;

    @MockitoBean
    private DepoimentosService depoimentosService;

    @Test
    @DisplayName("Deveria retornar o código http 200 quando postado informações válidas.")
    void postarDepoimento_cenario1() throws Exception {
        var response = mvc
                .perform(put("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(depoimentosCadastrarDTOJacksonTester.write(new DepoimentosCadastrarDTO(
                                "url.com",
                                "Muito Bom",
                                "João"
                        )).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 quando chamado a requisição get.")
    void mostrarDepoimentos_cenario1() throws Exception {
        var response = mvc.perform(get("/depoimentos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 com as alterações feitas corretamente.")
    void atualizar_cenario1() throws Exception {
        var detalhesDepoimentoDTO = new DepoimentosDetalhesDTO("Muito Bom", "João");

        when(depoimentosService.atualizarDados(any())).thenReturn(ResponseEntity.ok(detalhesDepoimentoDTO));

        var response = mvc
                .perform(put("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(depoimentosAtualizacaoDTOJacksonTester.write(new DepoimentosAtualizacaoDTO(
                        null,
                        "url.com",
                        "Muito Bom",
                        "João",
                        true
                )).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 204 quando endereço for correto pra exclusão.")
    void excluir_cenario1() throws Exception {
        when(depoimentosService.excluirDados(any())).thenReturn(ResponseEntity.noContent().build());
        var response = mvc.perform(delete("/depoimentos/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 quando chamado a requisição get.")
    void depoimentosSorteados_cenario1() throws Exception {
        var response = mvc.perform(get("/depoimentos/home")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}