package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TarefaService tarefaService;

    @Test
    void getAllTarefas() throws Exception {
        TarefaPrevista tarefa1=new TarefaPrevista();
        TarefaPrevista tarefa2=new TarefaPrevista();
        TarefaPrevista tarefa3=new TarefaPrevista();

        List<TarefaPrevista> tarefas= Arrays.asList(tarefa1,tarefa2,tarefa3);

        when(tarefaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString=mockMvc.perform(get("/tarefa")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }

    @Test
    void getTarefaById() throws Exception {
        TarefaPrevista tarefa = new TarefaPrevista();
        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefa);
        //System.out.println(tarefa);
        when(tarefaService.findById(1L)).thenReturn(Optional.of(tarefa));

        String httpResponseAsString=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());
    }

    @Test
    void patchTarefasEmpregado() {
    }

    @Test
    void criarTarefa() {
    }

    @Test
    void deleteTarefa() {
    }
}