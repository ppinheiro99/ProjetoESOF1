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
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.clientecases.facades.SearchClientesUseCase;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.*;

import javax.management.Query;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@SpringBootTest
class TarefaServiceImplTest {
    @Autowired
    private TarefaService tarefaService;

    private EliminarTarefaUseCase eliminarTarefaUseCase;
    private ListaTarefaPorIdUseCase listaTarefaPorIdUseCase;
    private ListTodasTarefasUseCase listTodasTarefasUseCase;
    private AtribuiTarefaEmpregado atribuiTarefaEmpregado;
    private AtribuiHorasTarefa atribuiHorasTarefa;
    private ConcluirTarefa concluirTarefa;
    private CriarTarefaUseCase criarTarefaUseCase;
    private SearchTarefasUseCase searchTarefasUseCase;

    @MockBean
    private EmpregadoRepository empregadoRepository;
    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @MockBean
    private TarefaEfetivaRepository tarefaEfetivaRepository;
    @MockBean
    private ProjetoRepository projetoRepository;

    @Test
    void findAll() {
        when(tarefaPrevistaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaService.findAll());
    }

    @Test
    void findById() {
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(tarefaService.findById(1L).isPresent());
        assertTrue(tarefaService.findById(2L).isEmpty());
    }

    @Test
    void atribuiTarefasEmpregados() {
        Projeto p = new Projeto();
        p.setId(1L);
        TarefaPrevista tp = new TarefaPrevista();
        tp.setId(1L);

        tp.setProjeto(p);
        tp.setNome("Nome");
        Empregado e = new Empregado();
        e.setNome("Nome1");
        e.setId(1L);
        e.setEmail("Email");
        Empregado e1 = new Empregado();
        e1.setEmail("Email1");

        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp));
        when(empregadoRepository.findByEmail(e.getEmail())).thenReturn(Optional.of(e));
        tarefaService.atribuiTarefasEmpregados(e.getEmail(),tp.getId());
        int size = e.getTarefaEfetivas().size();
        assertEquals(1,size);
        tarefaService.atribuiTarefasEmpregados(e1.getEmail(),tp.getId());
        size = e1.getTarefaEfetivas().size();
        assertNotEquals(1,size);
    }

    @Test
    void createTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        Projeto p1 = new Projeto();
        p1.setId(1L);
        tp1.setProjeto(p1);
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(p1));
        tarefaService.createTarefa(tp1);
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp1));
        assertTrue(tarefaPrevistaRepository.findById(1L).isPresent());
        //Empty
        TarefaPrevista tp2 = new TarefaPrevista();
        tp2.setId(2L);
        Projeto p2 = new Projeto();
        tp2.setProjeto(p2);
        tarefaService.createTarefa(tp2);
        assertTrue(tarefaPrevistaRepository.findById(2L).isEmpty());
        //
    }

    @Test
    void deleteTarefa() {
        Projeto p = new Projeto();
        p.setId(1L);
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        tp1.setProjeto(p);
        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);
        tp1.setTarefaEfetiva(te1);
        Empregado e = new Empregado();
        e.setId(1L);
        te1.setEmpregado(e);
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tp1));
        tarefaService.deleteTarefa(tp1.getId());
        assertNull(tp1.getProjeto()); // se for NUll é pq foi eliminada, pq nao pode existir tarefas sem projeto
    }

    @Test
    void atribuiHorasTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        tp1.atribuirTarefaEfetiva();
        Projeto p = new Projeto();
        tp1.setProjeto(p);
        when(tarefaEfetivaRepository.findById(1L)).thenReturn(Optional.of(tp1.getTarefaEfetiva()));
        tarefaService.atribuiHorasTarefa(tp1.getId(), 8.0f);
        assertEquals(8.0f,tp1.getTarefaEfetiva().getDuracaoHoras());
        // Empty
        assertTrue(tarefaService.atribuiHorasTarefa(2L, 8.0f).isEmpty());
        //
    }

    @Test
    void concluirTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        tp1.atribuirTarefaEfetiva();
        Projeto p = new Projeto();
        tp1.setProjeto(p);
        Empregado e1 = new Empregado();
        e1.setId(1L);
        tp1.setTempoPrevistoHoras(8);
        tp1.atribuirTarefaEfetiva();
        tp1.getTarefaEfetiva().setDuracaoHoras(8);
        tp1.setProjeto(p);
        tp1.getTarefaEfetiva().setEmpregado(e1);

        when(tarefaEfetivaRepository.findById(1L)).thenReturn(Optional.of(tp1.getTarefaEfetiva()));
        tarefaService.concluirTarefa(tp1.getTarefaEfetiva().getId());
        System.out.println( tp1.getTarefaEfetiva().estadoDaTarefa());
        assertEquals(Estados.Concluido, tp1.getTarefaEfetiva().estadoDaTarefa());
        // Empty
        assertTrue(tarefaService.concluirTarefa(2L).isEmpty());
        //
    }


    @Test
    void pesquisarTarefas() {
        TarefaPrevista tp = new TarefaPrevista();
        tp.setId(1L);
        tp.setNome("Tarefa1");
        tp.setId(1L);
        Projeto p1 = new Projeto();
        p1.setId(1L);

        tp.setProjeto(p1);

        Map<String,String> query = new HashMap<>();
        List<TarefaPrevista> tps = new ArrayList<>();
        // Caso o Id do projeto seja NULL
        query.put("nome", tp.getNome());
        System.out.println(query);

        // como a query nao é um mock nao da para fazer o when e como tal nao envia os dados
        tarefaService.pesquisarTarefas(query);

        // Com o ID do projeto
        query.put("idProjeto", String.valueOf(p1.getId()));
        tarefaService.pesquisarTarefas(query);
    }
}

