package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.controllers.TarefaController;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.clientecases.facades.SearchClientesUseCase;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



@SpringBootTest
@RunWith(SpringRunner.class)
class TarefaServiceImplTest {
    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private MockMvc mockMvc;

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
    private AtribuiHorasTarefa atribuiHorasTarefa;

    @MockBean
    private ConcluirTarefa concluirTarefa;
    @MockBean
    private SearchTarefasUseCase searchTarefasUseCase;


    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @MockBean
    private EmpregadoRepository empregadoRepository;
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
        tp.setNome("testeTarefa");
        Empregado e = new Empregado();
        e.setId(1L);
        e.setEmail("36763@ufp.edu.pt");

        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp));
//        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(e));
        Optional<Empregado> optionalEmpregado = tarefaService.atribuiTarefasEmpregados(e.getEmail(),tp.getId());

        System.out.println(optionalEmpregado.get().getNome());
        //System.out.println(optionalEmpregado.get().getNome());
        //when(atribuiTarefaEmpregado.atribuiTarefasEmpregados(e.getEmail(),tp.getId())).thenReturn(Optional.of(e));
        // System.out.println(optionalEmpregado);
        //assertTrue(tarefaService.atribuiTarefasEmpregados(e.getEmail(),tp.getId()).isPresent());
    }

    @Test
    void createTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        when(criarTarefaUseCase.createTarefa(tp1)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(criarTarefaUseCase.createTarefa(tp1).isPresent());
    }

    @Test
    void deleteTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        when(eliminarTarefaUseCase.deleteTarefa(1L)).thenReturn(Optional.of(tp1));
        assertEquals(eliminarTarefaUseCase.deleteTarefa(1L),Optional.of(tp1));
    }


    @Test
    void atribuiHorasTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        tp1.atribuirTarefaEfetiva();
        atribuiHorasTarefa.atribuiHoras(tp1.getId(), 8.0f);
        when(atribuiHorasTarefa.atribuiHoras(tp1.getId(), 8.0f)).thenReturn(Optional.of(tp1.getTarefaEfetiva()));
        System.out.println(tp1.getTempoPrevistoHoras());
        assertEquals(8.0f,tp1.getTempoPrevistoHoras());
    }

    @Test
    void concluirTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        tp1.setTempoPrevistoHoras(8);
        tp1.atribuirTarefaEfetiva();
        tp1.getTarefaEfetiva().setDuracaoHoras(8);
        when(concluirTarefa.terminarTarefa( tp1.getTarefaEfetiva().getId())).thenReturn(Optional.of( tp1.getTarefaEfetiva()));
        System.out.println( tp1.getTarefaEfetiva().estadoDaTarefa());
        assertEquals(Estados.Concluido, tp1.getTarefaEfetiva().estadoDaTarefa());
    }


    @Test
    void pesquisarTarefas() {
        TarefaPrevista t = new TarefaPrevista();
        t.setNome("Cliente2");
        t.setId(1L);
        Projeto p1 = new Projeto();

        // t.setProjeto(p1);

        Map<String, String> query = new HashMap<>();
        query.put("nome", t.getNome());


        List<TarefaPrevista> tarefas = new ArrayList<TarefaPrevista>();
        tarefas.add(t);
        // assertEquals(tarefaService.pesquisarTarefas(query), ;
    }
}

