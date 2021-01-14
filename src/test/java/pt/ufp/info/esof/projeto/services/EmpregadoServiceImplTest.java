package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;
    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void findAll() {
        when(empregadoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }
    @Test
    void findById() {
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent());
        assertTrue(empregadoService.findById(2L).isEmpty());
    }

    @Test
    void createEmpregado() {
        Empregado e1 = new Empregado();
        e1.setId(1L);
        e1.setEmail("Email");
        e1.setNome("Nome");
        e1.setCargo(Cargo.analistaJunior);
        empregadoService.createEmpregado(e1);
        when(empregadoRepository.findByEmail(e1.getEmail())).thenReturn(Optional.of(e1));
        assertTrue(empregadoRepository.findByEmail(e1.getEmail()).isPresent());
        //Empty
        empregadoService.createEmpregado(e1);
        assertTrue(empregadoRepository.findById(2L).isEmpty());
        //
    }

    @Test
    void deleteEmpregado() {
        Empregado e = new Empregado();
        e.setId(1L);
        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);
        e.getTarefaEfetivas().add(te1);
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        te1.setTarefaPrevista(tp1);

        when(empregadoRepository.findByEmail(e.getEmail())).thenReturn(Optional.of(e));
        empregadoService.deleteEmpregado(e.getEmail());
        assertTrue(empregadoRepository.findById(e.getId()).isEmpty());
    }

    @Test
    void pesquisarEmpregado() {
        Empregado e = new Empregado();
        e.setNome("Empregado");
        e.setEmail("email");
        e.setId(1L);
        e.setCargo(Cargo.analistaJunior);
        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);
        e.getTarefaEfetivas().add(te1);

        Map<String,String> query = new HashMap<>();
        List<TarefaPrevista> tps = new ArrayList<>();
        // Caso o Id do projeto seja NULL
        query.put("nome", e.getNome());
        query.put("email", e.getEmail());
        System.out.println(query);

        empregadoService.searchEmpregado(query);
        // com todos os campos
        query.put("idTarefaEfetiva", String.valueOf(e.getTarefaEfetivas().get(0).getId()));
        empregadoService.searchEmpregado(query);
    }
}