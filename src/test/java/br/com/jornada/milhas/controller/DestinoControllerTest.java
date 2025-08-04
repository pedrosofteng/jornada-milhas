package br.com.jornada.milhas.controller;

import br.com.jornada.milhas.config.ConfigEnv;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosCadastrarDTO;
import br.com.jornada.milhas.domain.depoimentos.DepoimentosService;
import br.com.jornada.milhas.domain.destinos.DestinoAtualizacaoDTO;
import br.com.jornada.milhas.domain.destinos.DestinoCadastrarDTO;
import br.com.jornada.milhas.domain.destinos.DestinoDetalhesDTO;
import br.com.jornada.milhas.domain.destinos.DestinoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DestinoControllerTest {

    @BeforeAll
    static void setupEnv() {
        ConfigEnv configEnv = new ConfigEnv();
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DestinoCadastrarDTO> destinoCadastrarDTOJacksonTester;

    @Autowired
    private JacksonTester<DestinoAtualizacaoDTO> destinoAtualizacaoDTOJacksonTester;

    @MockitoBean
    private DestinoService destinoService;

    @Test
    @DisplayName("Deveria retornar o código http 200 quando chamado a requisição get.")
    void verDestinos_cenario1() throws Exception {
        var response = mvc.perform(get("/destinos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 quando postado informações válidas.")
    void postarDestino_cenario1() throws Exception  {
        var response = mvc
                .perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinoCadastrarDTOJacksonTester.write(new DestinoCadastrarDTO(
                                "url.com",
                                "url.com2",
                                "meta",
                                24.0,
                                "Paris",
                                "textoDescritivo"
                        )).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 com as alterações feitas corretamente.")
    void alterarDestino_cenario1() throws Exception  {
        var destinoDetalhesDTO = new DestinoDetalhesDTO(
                "url.com",
                "url.com2",
                "meta",
                "textoDescritivo",
                "paris",
                24.0);
        when(destinoService.alterarDestino(any())).thenReturn(ResponseEntity.ok(destinoDetalhesDTO));
        // precisa especificar o que o metodo vai retornar

        var response = mvc
                .perform(put("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinoAtualizacaoDTOJacksonTester.write(new DestinoAtualizacaoDTO(
                                1L,
                                "url.com",
                                "url.com2",
                                "meta",
                                "textoDescritivo",
                                "Paris",
                                24.0
                                )
                        ).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 204 quando endereço for correto pra exclusão.")
    void excluir_cenario1() throws Exception  {
        when(destinoService.excluir(1L)).thenReturn(ResponseEntity.noContent().build());
        var response = mvc.perform(delete("/destinos/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deveria retornar o código http 200 quando chamado a requisição get.")
    void mostrarDestinoEspecifico_cenario1() throws Exception  {
        var response = mvc.perform(get("/destinos?nome=Paris")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}