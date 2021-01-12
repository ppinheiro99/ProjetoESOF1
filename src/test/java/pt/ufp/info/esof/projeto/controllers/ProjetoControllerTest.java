package pt.ufp.info.esof.projeto.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.CriarProjetoDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.*;

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
    void getAllProjetos() throws Exception {
        Projeto projeto = new Projeto();
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);
        c1.getProjetos().add(projeto);

        Projeto projeto2 = new Projeto();
        String projeto2AsJsonString = new ObjectMapper().writeValueAsString(projeto2);
        Cliente c2 = new Cliente();
        projeto2.setCliente(c2);
        c2.getProjetos().add(projeto2);
        List<Projeto> projetos= Arrays.asList(projeto,projeto2);

        when(projetoService.findAll()).thenReturn(projetos);
        String httpResponseAsString=mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getProjetoById() throws Exception {
        Projeto projeto = new Projeto();
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);
        c1.getProjetos().add(projeto);

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));
        String httpResponseAsString=mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }

    @Test
    void getCustoProjeto() throws Exception {
        Projeto projeto = new Projeto();
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);
        c1.getProjetos().add(projeto);

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/valor")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getDuracaoProjeto() throws Exception {
        Projeto projeto = new Projeto();
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);
        c1.getProjetos().add(projeto);

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/tempo")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void criarProjeto() throws Exception {
        Projeto projeto = new Projeto();

        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);
        c1.getProjetos().add(projeto);

        CriarProjetoDTO projetoDTO=new CriarProjetoDTO();
        projetoDTO.setNome("teste");
        projetoDTO.setClienteID(1L);

        when(this.projetoService.criarProjeto(projetoDTO.converter())).thenReturn(Optional.of(projeto));
        String projetoAsJsonString=new ObjectMapper().writeValueAsString(projetoDTO);
        mockMvc.perform(post("/projeto").content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    void associarProjetoTarefa() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);
        tarefa.setNome("teste");
        tarefa.setTempoPrevistoHoras(10);
        tarefa.atribuirTarefaEfetiva();

        when(projetoService.assocTarefasProjeto(projeto.getId(),tarefa.getId())).thenReturn(Optional.of(projeto));
        String projetoAsJsonString=new ObjectMapper().writeValueAsString(projeto);

        mockMvc.perform(patch("/projeto/"+ projeto.getId() +"/" + tarefa.getId()).content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void deleteProjeto() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        projeto.setCliente(c1);

        when(projetoService.findById(projeto.getId())).thenReturn(Optional.of(projeto));
        String projetoAsJsonString=new ObjectMapper().writeValueAsString(projeto);
        mockMvc.perform(delete("/projeto/"+projeto.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(projetoAsJsonString)).andExpect(status().isOk());
    }

    @Test
    void searchProjeto() throws Exception {
        Projeto p = new Projeto();
        p.setNome("ESOF");
        p.setId(1L);
        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("Cliente2");
        c.setEmail("cliente2@teste.com");
        Map<String, String> query = new HashMap<>();
        query.put("query[nome]",c.getNome());

        p.setCliente(c);
        List<Projeto> projetos = Collections.singletonList(p);

        when(this.projetoService.searchProjeto(query)).thenReturn(projetos);
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(projetos);
        mockMvc.perform(get("/projeto/search").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}