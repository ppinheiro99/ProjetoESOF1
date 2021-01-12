package pt.ufp.info.esof.projeto.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.CriarEmpregadoDTO;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.services.EmpregadoService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmpregadoService empregadoService;

    @Test
    void testGetAllEmpregados() throws Exception {
        Empregado empregado1=new Empregado();
        Empregado empregado2=new Empregado();
        Empregado empregado3=new Empregado();

        List<Empregado> empregados= Arrays.asList(empregado1,empregado2,empregado3);
        when(empregadoService.findAll()).thenReturn(empregados);
        String httpResponseAsString=mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void testGetEmpregadoById() throws Exception {
        Empregado empregado = new Empregado();
        String empregadoAsJsonString = new ObjectMapper().writeValueAsString(empregado);
        when(empregadoService.findById(1L)).thenReturn(Optional.of(empregado));
        String httpResponseAsString=mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }

    @Test
    void testCreateEmpregado() throws Exception {
        Empregado empregado = new Empregado();
        CriarEmpregadoDTO empregadoDTO=new CriarEmpregadoDTO();
        empregadoDTO.setNome("teste");
        empregadoDTO.setCargo(Cargo.desenvolvedorJunior);
        empregadoDTO.setEmail("email");

        when(this.empregadoService.createEmpregado(empregadoDTO.converter())).thenReturn(Optional.of(empregado));

        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregadoDTO);

        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void deleteEmpregado()throws Exception{
        Empregado e = new Empregado();
        e.setEmail("teste");
        e.setNome("teste");
        TarefaPrevista tp = new TarefaPrevista();
        TarefaEfetiva te = new TarefaEfetiva();
        te.setTarefaPrevista(tp);
        e.getTarefaEfetivas().add(te);

        when(this.empregadoService.deleteEmpregado(e.getEmail())).thenReturn(Optional.of(e));

        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(e);

        mockMvc.perform(delete("/empregado/"+e.getEmail()).content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void searchEmpregado() throws Exception {
        Empregado e = new Empregado();
        e.setNome("Teste");
        e.setId(1L);
        e.setEmail("teste@teste.com");
        Map<String, String> query = new HashMap<>();
        query.put("query[nome]",e.getNome());

        List<Empregado> empregados = Collections.singletonList(e);

        when(this.empregadoService.searchEmpregado(query)).thenReturn(empregados);
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregados);
        mockMvc.perform(get("/empregado/search").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}