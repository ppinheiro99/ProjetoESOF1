package pt.ufp.info.esof.projeto.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjetoService projetoService;

    @Test
    void getAllProjetos() {
    }

    @Test
    void getProjetoById() throws Exception {
        Projeto projeto = new Projeto();
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        //System.out.println(tarefa);
        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));

        String httpResponseAsString=mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }

    @Test
    void getCustoProjeto() {
    }

    @Test
    void getDuracaoProjeto() {
    }

    @Test
    void criarProjeto() {
    }

    @Test
    void adicionaTarefa() {
    }

    @Test
    void associarProjetoTarefa() {
    }
}