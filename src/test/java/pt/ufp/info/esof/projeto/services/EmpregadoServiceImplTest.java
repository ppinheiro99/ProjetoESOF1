package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
<<<<<<< HEAD
import org.springframework.boot.test.context.SpringBootTest;
=======
>>>>>>> master
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
<<<<<<< HEAD
import pt.ufp.info.esof.projeto.services.clientecases.facades.*;
import pt.ufp.info.esof.projeto.services.empregado.facades.*;
=======
import pt.ufp.info.esof.projeto.services.empregadocases.facades.*;
>>>>>>> master

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@WebMvcTest(EmpregadoServiceFacades.class)
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
<<<<<<< HEAD
    private EmpregadoServiceFacades empregadoServiceImpl;

    @MockBean
    private EliminarEmpregadoUseCase eliminarEmpregadoUseCase;

=======
    private EliminarEmpregadoUseCase eliminarEmpregadoUseCase;
>>>>>>> master
    @MockBean
    private CriarEmpregadoUseCase criarEmpregadoUseCase;
    @MockBean
    private ListaEmpregadoPorIdUseCase listaEmpregadoPorIdUseCase;
    @MockBean
    private ListTodosEmpregadosUseCase listTodosEmpregadosUseCase;
<<<<<<< HEAD


=======
>>>>>>> master

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