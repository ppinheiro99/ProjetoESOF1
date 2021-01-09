package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.CriarTarefaPrevistaDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefa1);
        Projeto p1 = new Projeto();
        tarefa1.setProjeto(p1);
        p1.getTarefaPrevistas().add(tarefa1);
        TarefaPrevista tarefa2=new TarefaPrevista();
        String tarefa2AsJsonString = new ObjectMapper().writeValueAsString(tarefa2);
        Projeto p2 = new Projeto();
        tarefa2.setProjeto(p2);
        p1.getTarefaPrevistas().add(tarefa2);


        List<TarefaPrevista> tarefas= Arrays.asList(tarefa1,tarefa2);

        when(tarefaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString=mockMvc.perform(get("/tarefa")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }

    @Test
    void getTarefaById() throws Exception {
        TarefaPrevista tarefa = new TarefaPrevista();
        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefa);
        Projeto p1 = new Projeto();
        tarefa.setProjeto(p1);
        p1.getTarefaPrevistas().add(tarefa);
        when(tarefaService.findById(1L)).thenReturn(Optional.of(tarefa));

        String httpResponseAsString=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());
    }

    @Test
    void criarTarefa() throws Exception {
        TarefaPrevista tarefa = new TarefaPrevista();
        Projeto p1 = new Projeto();
        tarefa.setProjeto(p1);
        tarefa.setNome("teste");
        tarefa.setTempoPrevistoHoras(10);
        p1.getTarefaPrevistas().add(tarefa);
        p1.setId(1L);

        CriarTarefaPrevistaDTO tarefaDTO=new CriarTarefaPrevistaDTO();
        tarefaDTO.setNome("teste");
        tarefaDTO.setDuracaoHoras(10);
        tarefaDTO.setProjetoID(1L);

        when(this.tarefaService.createTarefa(tarefaDTO.converter())).thenReturn(Optional.of(tarefa));

        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(tarefaDTO);

        mockMvc.perform(post("/tarefa").content(tarefaAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void deleteTarefa() throws Exception {
        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);
        Projeto p1 = new Projeto();
        tarefa.setProjeto(p1);
        tarefa.setNome("teste");
        tarefa.setTempoPrevistoHoras(10);

        p1.setId(1L);

        when(tarefaService.findById(tarefa.getId())).thenReturn(Optional.of(tarefa));
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(tarefa);
        mockMvc.perform(delete("/tarefa/"+tarefa.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isOk());
    }

    @Test
    void testPatchTarefasEmpregado() throws Exception {
        Empregado e1 = new Empregado();
        e1.setEmail("teste");
        e1.setNome("teste");
        Cliente c1 = new Cliente();
        Projeto p1 = new Projeto();
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);

        tp1.setProjeto(p1);
        p1.setCliente(c1);

        when(tarefaService.findById(tp1.getId())).thenReturn(Optional.of(tp1));
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(tp1);
        mockMvc.perform(patch("/tarefa/"+e1.getEmail()+"/"+tp1.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isBadRequest());
    }
}