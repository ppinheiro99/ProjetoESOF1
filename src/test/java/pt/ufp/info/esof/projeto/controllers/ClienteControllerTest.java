package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ufp.info.esof.projeto.dtos.CriarClienteDTO;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.services.ClienteService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

    @Test
    void getAllClientes() throws Exception {
        Cliente c1=new Cliente();
        Cliente c2=new Cliente();
        Cliente c3=new Cliente();

        List<Cliente> clientes= Arrays.asList(c1,c2,c3);
        when(clienteService.findAll()).thenReturn(clientes);
        String httpResponseAsString=mockMvc.perform(get("/cliente")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getClienteById() throws Exception {
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        c1.setNome("teste");
        String clienteAsJsonString = new ObjectMapper().writeValueAsString(c1);

        when(clienteService.findById(1L)).thenReturn(Optional.of(c1));
        String httpResponseAsString=mockMvc.perform(get("/cliente/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/cliente/2")).andExpect(status().isNotFound());
    }

    @Test
    void createCliente() throws Exception {
        Cliente c1 = new Cliente();
        c1.setEmail("teste");
        c1.setNome("teste");

        CriarClienteDTO clienteDTO=new CriarClienteDTO();
        clienteDTO.setNome("teste");
        clienteDTO.setEmail("teste");

        when(this.clienteService.createCliente(clienteDTO.converter())).thenReturn(Optional.of(c1));
        String clienteAsJsonString=new ObjectMapper().writeValueAsString(clienteDTO);
        mockMvc.perform(post("/cliente").content(clienteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void deleteCliente() throws Exception{
        Cliente c = new Cliente();
        c.setEmail("teste");
        c.setNome("teste");

        when(this.clienteService.deleteCliente(c.getId())).thenReturn(Optional.of(c));
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(c);
        mockMvc.perform(delete("/cliente/"+c.getId()).content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void searchCliente() throws Exception{
        Cliente c = new Cliente();
        c.setNome("Cliente2");
        c.setId(1L);
        c.setEmail("cliente2@teste.com");
        Map<String, String> query = new HashMap<>();
        query.put("query[email]",c.getEmail());

        List<Cliente> clientes = Collections.singletonList(c);

        when(this.clienteService.searchCliente(query)).thenReturn(clientes);
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(clientes);
        mockMvc.perform(get("/cliente/search").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}