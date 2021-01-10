package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.clientecases.facades.ClienteServiceFacades;
import pt.ufp.info.esof.projeto.services.empregadocases.facades.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = EmpregadoServiceFacades.class)
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;
    @MockBean
    private EliminarEmpregadoUseCase eliminarEmpregadoUseCase;
    @MockBean
    private CriarEmpregadoUseCase criarEmpregadoUseCase;
    @MockBean
    private ListaEmpregadoPorIdUseCase listaEmpregadoPorIdUseCase;
    @MockBean
    private ListTodosEmpregadosUseCase listTodosEmpregadosUseCase;

    @Test
    void findAll() {
        when(listTodosEmpregadosUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }
    @Test
    void findById() {
        when(listaEmpregadoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent());
        assertTrue(empregadoService.findById(2L).isEmpty());
    }

    @Test
    void createEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");

        when(criarEmpregadoUseCase.createEmpregado(e)).thenReturn(Optional.of(e));

        assertTrue(empregadoService.createEmpregado(e).isPresent());
    }

    @Test
    void deleteEmpregado() {
        Empregado e = new Empregado();
        e.setEmail("mail");
        when(eliminarEmpregadoUseCase.deleteEmpregado(e.getEmail())).thenReturn(Optional.of(e));
        assertTrue(empregadoService.deleteEmpregado(e.getEmail()).isPresent());
    }
}