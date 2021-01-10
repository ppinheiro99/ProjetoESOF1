package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TarefaServiceFacades.class)
class TarefaServiceImplTest {

    @Autowired
    private TarefaService tarefaService;

    @MockBean
    private  EliminarTarefaUseCase eliminarTarefaUseCase;
    @MockBean
    private  CriarTarefaUseCase criarTarefaUseCase;
    @MockBean
    private  ListaTarefaPorIdUseCase listaTarefaPorIdUseCase;
    @MockBean
    private  ListTodasTarefasUseCase listTodasTarefasUseCase;
    @MockBean
    private  AtribuiTarefaEmpregado atribuiTarefaEmpregado;
    @MockBean
    private  TarefaPrevistaRepository tarefaPrevistaRepository;
    @MockBean
    private  EmpregadoRepository empregadoRepository;

    @Test
    void findAll() {
        when(listTodasTarefasUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaService.findAll());
    }

    @Test
    void findById() {
        when(listaTarefaPorIdUseCase.findById(1L)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(tarefaService.findById(1L).isPresent());
        assertTrue(tarefaService.findById(2L).isEmpty());
    }

    @Test
    void atribuiTarefasEmpregados() {
        TarefaPrevista tp = new TarefaPrevista();
        tp.setId(1L);
        Empregado e = new Empregado();
        e.setId(1L);
        e.setEmail("teste");
//        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp));
//        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(e));
//        Optional<Empregado> optionalEmpregado = atribuiTarefaEmpregado.atribuiTarefasEmpregados(e.getEmail(),tp.getId());
//        if(optionalEmpregado.isEmpty()){
//            System.out.println("nao vou adicionada a ligacao entre eles");
//        }
    }

    @Test
    void createTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        when(criarTarefaUseCase.createTarefa(tp1)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(tarefaService.createTarefa(tp1).isPresent());
    }

    @Test
    void deleteTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        when(eliminarTarefaUseCase.deleteTarefa(1L)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(tarefaService.deleteTarefa(1L).isPresent());
    }
}