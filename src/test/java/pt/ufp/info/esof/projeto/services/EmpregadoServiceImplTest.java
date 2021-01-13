package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.empregadocases.facades.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;
    @Mock
    private EliminarEmpregadoUseCase eliminarEmpregadoUseCase;
    @Mock
    private CriarEmpregadoUseCase criarEmpregadoUseCase;
    @Mock
    private ListaEmpregadoPorIdUseCase listaEmpregadoPorIdUseCase;
    @Mock
    private ListTodosEmpregadosUseCase listTodosEmpregadosUseCase;
    @Mock
    private SearchEmpregadosUseCase searchEmpregadosUseCase;

    @Test
    void findAll() {
        when(listTodosEmpregadosUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }
    @Test
    void findById() {
        when(listaEmpregadoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(listaEmpregadoPorIdUseCase.findById(1L).isPresent());
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
        assertTrue(eliminarEmpregadoUseCase.deleteEmpregado(e.getEmail()).isPresent());
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
        when(searchEmpregadosUseCase.pesquisarEmpregado(query)).thenReturn(empregados);
        assertEquals(searchEmpregadosUseCase.pesquisarEmpregado(query),empregados);

    }
}