package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaServiceFacades.class)
class TarefaServiceImplTest {
    @Autowired
    private TarefaService tarefaService;
    @MockBean
    private EliminarTarefaUseCase eliminarTarefaUseCase;
    @MockBean
    private CriarTarefaUseCase criarTarefaUseCase;
    @MockBean
    private ListaTarefaPorIdUseCase listaTarefaPorIdUseCase;
    @MockBean
    private ListTodasTarefasUseCase listTodasTarefasUseCase;
    @MockBean
    private AtribuiTarefaEmpregado atribuiTarefaEmpregado;
    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @MockBean
    private AtribuiHorasTarefa atribuiHorasTarefa;
    @MockBean
    private EmpregadoRepository empregadoRepository;
    @MockBean
    private ConcluirTarefa concluirTarefa;
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

       when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp));
       when(empregadoRepository.findById(1L)).thenReturn(Optional.of(e));
       when(atribuiTarefaEmpregado.atribuiTarefasEmpregados(e.getEmail(),tp.getId())).thenReturn(Optional.of(new Empregado()));

       assertTrue(tarefaService.atribuiTarefasEmpregados(e.getEmail(),tp.getId()).isPresent());

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


    @Test
    void atribuiHorasTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        when(atribuiHorasTarefa.atribuiHoras(tp1.getId(),8.0f)).thenReturn(Optional.of(new TarefaEfetiva()));
        assertTrue(tarefaService.atribuiHorasTarefa(tp1.getId(),8.0f).isPresent());
    }

    @Test
    void concluirTarefa() {
        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);
        Empregado e1 = new Empregado();
        e1.setId(1L);

        te1.setEmpregado(e1);
        te1.setDuracaoHoras(6);

        when(concluirTarefa.terminarTarefa(te1.getId())).thenReturn(Optional.of(te1));
        assertTrue(tarefaService.concluirTarefa(te1.getId()).isPresent());
    }

}