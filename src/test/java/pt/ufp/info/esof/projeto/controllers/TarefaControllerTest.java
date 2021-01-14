package pt.ufp.info.esof.projeto.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.CriarTarefaPrevistaDTO;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        when(tarefaService.atribuiTarefasEmpregados(e1.getEmail(), tp1.getId())).thenReturn(Optional.of(e1));
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(e1);
        mockMvc.perform(patch("/tarefa/"+e1.getEmail()+"/"+tp1.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isOk());
    }

    @Test
    void atribuiHorasTarefa()  throws Exception{
        Projeto p = new Projeto();
        p.setId(1L);

        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);

        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);

        Empregado e1 = new Empregado();
        e1.setId(1L);
        e1.setEmail("1111");

        te1.setTarefaPrevista(tp1);
        te1.setEmpregado(e1);
        tp1.setProjeto(p);
        float duracao = 8.0f;

        when(tarefaService.atribuiHorasTarefa(te1.getId(),duracao)).thenReturn(Optional.of(te1));
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(te1);
        mockMvc.perform(patch("/tarefa/"+"/"+te1.getId()+"/"+duracao+"/tempo").contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isOk());
        mockMvc.perform(patch("/tarefa/"+"/"+8+"/"+duracao+"/tempo").contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isNotFound());

    }

    @Test
    void concluirTarefa() throws Exception {
        Projeto p = new Projeto();
        p.setId(1L);

        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);

        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);

        Empregado e1 = new Empregado();
        e1.setId(1L);
        e1.setEmail("1111");

        te1.setTarefaPrevista(tp1);
        te1.setEmpregado(e1);
        tp1.setProjeto(p);

        when(tarefaService.concluirTarefa(te1.getId())).thenReturn(Optional.of(te1));
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(te1);

        mockMvc.perform(patch("/tarefa/" +te1.getId() +"/concuirTarefa").contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isOk());
        mockMvc.perform(patch("/tarefa/" +8 +"/concuirTarefa").contentType(MediaType.APPLICATION_JSON_VALUE).content(tarefaAsJsonString)).andExpect(status().isNotFound());

    }

    @Test
    void searchTarefas() throws Exception {
        TarefaPrevista t = new TarefaPrevista();
        t.setNome("teste");
        t.setId(1L);
        Map<String, String> query = new HashMap<>();
        query.put("query[nome]",t.getNome());

        List<TarefaPrevista> tarefaPrevistas = Collections.singletonList(t);

        when(this.tarefaService.pesquisarTarefas(query)).thenReturn(tarefaPrevistas);
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(tarefaPrevistas);
        mockMvc.perform(get("/tarefa/search").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}