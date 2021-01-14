package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProjetoServiceImplTest {
    @Autowired
    private ProjetoService projetoService;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    void findAll() {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertTrue(projetoService.findById(2L).isEmpty());
    }

    @Test
    void deleteProjeto() {
        Cliente c = new Cliente();
        c.setId(1L);
        Projeto p = new Projeto();
        p.setId(1L);
        p.setCliente(c);
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setId(1L);
        p.getTarefaPrevistas().add(tp1);

        TarefaEfetiva te1 = new TarefaEfetiva();
        te1.setId(1L);

        tp1.setTarefaEfetiva(te1);
        Empregado e = new Empregado();
        e.setId(1L);
        te1.setEmpregado(e);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(p));
        projetoService.deleteProjeto(p.getId());
        assertNull(p.getCliente()); // se for NUll é pq foi eliminada, isto pq deixou de ter clientes associados
        //Empty
        assertTrue(projetoService.deleteProjeto(2L).isEmpty());
        //
    }

    @Test
    void criarProjeto() {
        Projeto p = new Projeto();
        p.setId(1L);
        projetoService.criarProjeto(p);
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(p));
        assertTrue(projetoRepository.findById(1L).isPresent());
        //Empty
        projetoService.criarProjeto(p);
        assertTrue(projetoRepository.findById(2L).isEmpty());
        //
    }

    @Test
    void custoPrevistoProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);
        tarefa.setTempoPrevistoHoras(8);
        Empregado e1 = new Empregado();
        e1.setCargo(Cargo.desenvolvedorSenior);
        tarefa.atribuirTarefaEfetiva();
        tarefa.getTarefaEfetiva().setEmpregado(e1);

        float valor = tarefa.custoPrevistoTarefa() ;
        System.out.println(valor);
        System.out.println(valor);
        projeto.getTarefaPrevistas().add(tarefa);
        tarefa.setProjeto(projeto);
        when(projetoRepository.findById(projeto.getId())).thenReturn(Optional.of(projeto));
        projetoService.custoPrevistoProjeto(projeto.getId());
        assertEquals(valor,projetoService.custoPrevistoProjeto(projeto.getId()));
        // Empty
        projetoService.custoPrevistoProjeto(2L);
        assertEquals(0f,projetoService.custoPrevistoProjeto(2L));
    }

    @Test
    void duracaoPrevistaProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);
        tarefa.setTempoPrevistoHoras(8);

        float valor = tarefa.getTempoPrevistoHoras(); // como o projeto so tem 1 tarefa vai ter esta duracao
        projeto.getTarefaPrevistas().add(tarefa);

        when(projetoRepository.findById(projeto.getId())).thenReturn(Optional.of(projeto));
        projetoService.custoPrevistoProjeto(projeto.getId());
        assertEquals(valor,projetoService.duracaoPrevistaProjeto(projeto.getId()));
        // Empty
        projetoService.duracaoPrevistaProjeto(2L);
        assertEquals(0,projetoService.custoPrevistoProjeto(2L));
    }

    @Test
    void assocTarefasProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);

        when(projetoRepository.findById(projeto.getId())).thenReturn(Optional.of(projeto));
        when(tarefaPrevistaRepository.findById(tarefa.getId())).thenReturn(Optional.of(tarefa));

        assertTrue(projetoService.assocTarefasProjeto(projeto.getId(),tarefa.getId()).isPresent());
        assertTrue(projetoService.assocTarefasProjeto(2L,2L).isEmpty());
    }
    @Test
    void pesquisarProjeto(){
        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("cliente");
        Projeto p1 = new Projeto();
        p1.setId(1L);
        p1.setEstadoProjeto(Estados.Concluido);

        p1.setCliente(c);

        Map<String,String> query = new HashMap<>();
        List<TarefaPrevista> tps = new ArrayList<>();

        query.put("nome", p1.getNome());
        query.put("nomeCliente", c.getNome());
        projetoService.searchProjeto(query);

        // Com tudo
        query.put("estado", String.valueOf(p1.getId()));
        projetoService.searchProjeto(query);
    }
}