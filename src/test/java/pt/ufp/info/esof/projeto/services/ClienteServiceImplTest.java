package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
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
    @MockBean
    private ClienteRepository clienteRepository;



    @Test
    void findAll() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteRepository.findAll());
    }

    @Test
    void findById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteRepository.findById(1L).isPresent());
    }

    @Test
    void createCliente() {
        /*Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.getProjetos().add(new Projeto());*/
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        //clienteService.createCliente(new Cliente());
        assertEquals(clienteRepository.findById(1L),Optional.of(new Cliente()));
    }

    @Test
    void deleteCliente() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        Projeto projeto = new Projeto();
        c1.getProjetos().add(projeto);
        projeto.setCliente(c1);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(c1));
        clienteService.deleteCliente(1L);
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
        when(clienteService.searchCliente(query)).thenReturn(clientes);
        assertEquals(clienteService.searchCliente(query),clientes);
    }
}