package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ClienteServiceImplTest {
    @Autowired
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    void findAll() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.findById(1L).isPresent());
        assertTrue(clienteService.findById(2L).isEmpty());
    }

    @Test
    void createCliente() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setEmail("email");
        c1.setNome("nome");
        clienteService.createCliente(c1);
        when(clienteRepository.findByEmail(c1.getEmail())).thenReturn(Optional.of(c1));
        assertTrue(clienteRepository.findByEmail(c1.getEmail()).isPresent());
        //Empty
        clienteService.createCliente(c1);
        assertTrue(clienteRepository.findById(2L).isEmpty());
        //
    }


    @Test
    void deleteCliente() {
        Cliente c = new Cliente();
        c.setId(1L);
        Projeto p = new Projeto();
        p.setId(1L);
        p.setNome("ProjetoTeste");

        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);

        p.getTarefaPrevistas().add(tp1);
        tp1.setProjeto(p);

        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);

        tp1.setTarefaEfetiva(te1);
        Empregado e = new Empregado();
        e.setId(1L);
        te1.setEmpregado(e);

        c.getProjetos().add(p);
        p.setCliente(c);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(c));
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(p));
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp1));
        clienteService.deleteCliente(c.getId());
        assertNull(p.getCliente()); // se for NUll é pq foi eliminada, isto pq deixou de ter clientes associados
        //Empty
        assertTrue(clienteService.deleteCliente(2L).isEmpty());
        //

    }
    @Test
    void pesquisarCliente(){
        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("cliente");
        Projeto p1 = new Projeto();
        p1.setNome("projeto");
        p1.setId(1L);
        p1.setEstadoProjeto(Estados.Concluido);

        c.getProjetos().add(p1);

        Map<String,String> query = new HashMap<>();
        List<TarefaPrevista> tps = new ArrayList<>();

        query.put("nome", c.getNome());
        query.put("email", c.getEmail());
        query.put("nomeProjeto", p1.getNome());
        clienteService.searchCliente(query);
        // Com tudo
        query.put("idProjeto", String.valueOf(p1.getId()));
        clienteService.searchCliente(query);
    }
}