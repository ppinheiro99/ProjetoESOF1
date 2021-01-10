package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.clientecases.facades.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@WebMvcTest(ClienteServiceFacades.class)
class ClienteServiceImplTest {
    @Autowired
    private ClienteService clienteService;
    @MockBean
    private EliminarClienteUseCase eliminarClienteUseCase;
    @MockBean
    private CriarClienteUseCase criarClienteUseCase;
    @MockBean
    private ListaClientePorIdUseCase listaClientePorIdUseCase;
    @MockBean
    private ListTodosClientesUseCase listTodosClientesUseCase;

    @Test
    void findAll() {
        when(listTodosClientesUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {
        when(listaClientePorIdUseCase.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.findById(1L).isPresent());
        assertTrue(clienteService.findById(2L).isEmpty());
    }

    @Test
    void createCliente() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        when(criarClienteUseCase.createCliente(c1)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.createCliente(c1).isPresent());
    }

    @Test
    void deleteCliente() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        when(eliminarClienteUseCase.deleteCliente(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.deleteCliente(1L).isPresent());
    }
}