package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;
    @Mock
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
    }

    @Test
    void createEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");
        assertTrue(empregadoService.createEmpregado(e).isPresent());
    }

    @Test
    void deleteEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");
        when(empregadoRepository.pesquisaEmpregados(e.getNome(),e.getEmail(),null,null)).thenReturn(empregados);
       // assertTrue(empregadoService.deleteEmpregado(e.getEmail()).isPresent());
    }

    @Test
    void pesquisarEmpregado() {
        Empregado e = new Empregado();
        e.setNome("Empregado1");
        e.setId(1L);
        e.setEmail("Empregado1@teste.com");
        Map<String, String> query = new HashMap<>();
        query.put("email",e.getEmail());
        ArrayList<Empregado> empregados = new ArrayList<>();
        empregados.add(e);
        when(empregadoRepository.pesquisaEmpregados(e.getNome(),e.getEmail(),null,null)).thenReturn(empregados);
       // assertEquals(empregadoService.searchEmpregado(query),empregados);

    }
}