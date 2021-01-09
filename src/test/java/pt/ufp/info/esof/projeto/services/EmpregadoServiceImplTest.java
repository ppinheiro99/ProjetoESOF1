package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Empregado;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {

    @Autowired
    private EmpregadoService empregadoService;
    @MockBean
    private EmpregadoServiceImpl empregadoServiceImpl;

    @Test
    void findAll() {
        when(empregadoServiceImpl.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }

    @Test
    void findById() {
        when(empregadoServiceImpl.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent());
        assertTrue(empregadoService.findById(2L).isEmpty());
    }

    @Test
    void createEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");

        when(empregadoServiceImpl.createEmpregado(e)).thenReturn(Optional.of(e));

        assertTrue(empregadoService.createEmpregado(e).isPresent());
    }

    @Test
    void deleteEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");

        when(empregadoServiceImpl.deleteEmpregado(e.getEmail())).thenReturn(Optional.of(e));

        assertTrue(empregadoService.deleteEmpregado(e.getEmail()).isPresent());
    }
}