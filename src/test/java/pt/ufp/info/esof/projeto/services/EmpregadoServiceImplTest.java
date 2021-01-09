package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.controllers.EmpregadoController;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {

    @Autowired
    private EmpregadoService empregadoService;
    @MockBean
    private EmpregadoServiceImpl empregadoServiceImpl;

    @Test
    void findAll() {
        when(empregadoServiceImpl.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoServiceImpl.findAll());
    }

    @Test
    void findById() {
        when(empregadoServiceImpl.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoServiceImpl.findById(1L).isPresent());
        assertTrue(empregadoServiceImpl.findById(2L).isEmpty());
    }

    @Test
    void createEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");

        when(empregadoServiceImpl.createEmpregado(e)).thenReturn(Optional.of(e));

        assertTrue(empregadoServiceImpl.createEmpregado(e).isPresent());
    }

    @Test
    void deleteEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");

        when(empregadoServiceImpl.deleteEmpregado(e.getEmail())).thenReturn(Optional.of(e));

        assertTrue(empregadoServiceImpl.deleteEmpregado(e.getEmail()).isPresent());
    }
}