package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.clientecases.facades.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ClienteServiceImplTest {
    @Autowired
    private ClienteService clienteService;
    @Mock
    private EliminarClienteUseCase eliminarClienteUseCase;
    @Mock
    private CriarClienteUseCase criarClienteUseCase;
    @Mock
    private ListaClientePorIdUseCase listaClientePorIdUseCase;
    @Mock
    private ListTodosClientesUseCase listTodosClientesUseCase;
    @Mock
    private SearchClientesUseCase searchClientesUseCase;

    @Test
    void findAll() {
        when(listTodosClientesUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {
        when(listaClientePorIdUseCase.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(listaClientePorIdUseCase.findById(1L).isPresent());
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
        when(eliminarClienteUseCase.deleteCliente(1L)).thenReturn(Optional.of(c1));
        assertTrue(eliminarClienteUseCase.deleteCliente(1L).isPresent());
    }
    @Test
    void pesquisarCliente(){
        Cliente c = new Cliente();
        c.setNome("Cliente2");
        c.setId(1L);
        c.setEmail("cliente2@teste.com");
        Map<String, String> query = new HashMap<>();
        query.put("email",c.getEmail());
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(c);
        when(searchClientesUseCase.pesquisarCliente(query)).thenReturn(clientes);
        assertEquals(searchClientesUseCase.pesquisarCliente(query),clientes);
    }
}