package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@WebMvcTest(ClienteService.class)
class ClienteServiceImplTest {
    @MockBean
    private ClienteService clienteService;


    @Test
    void findAll() {
        when(clienteService.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {
        when(clienteService.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.findById(1L).isPresent());
        assertTrue(clienteService.findById(2L).isEmpty());
    }

    @Test
    void createCliente() {
        Cliente c1 = new Cliente();
        when(clienteService.createCliente(c1)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.createCliente(c1).isPresent());
    }

    @Test
    void deleteCliente() {
        when(clienteService.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.deleteCliente(1L).isEmpty());
    }
}