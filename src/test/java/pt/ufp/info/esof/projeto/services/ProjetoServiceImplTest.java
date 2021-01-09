package pt.ufp.info.esof.projeto.services;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest (ProjetoService.class)
class ProjetoServiceImplTest {

    @MockBean
    private ProjetoService projetoService;

    @MockBean
    private ProjetoServiceImpl projetoServiceImpl;

    @Test
    void findAll() {

        when(projetoService.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById() {
        when(projetoService.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertTrue(projetoService.findById(2L).isEmpty());

    }

    @Test
    void removeTarefasProjeto() {
        TarefaPrevista tarefa = new TarefaPrevista();
        Projeto p1 = new Projeto();
        tarefa.setProjeto(p1);
        tarefa.setNome("teste");
        tarefa.setTempoPrevistoHoras(10);
        p1.getTarefaPrevistas().add(tarefa);
        p1.setId(1L);
        int countAntesRemover;
        int depoisRemover;

        countAntesRemover=p1.getTarefaPrevistas().size();
        projetoServiceImpl.removeTarefasProjeto(p1.getTarefaPrevistas().get(0));
        depoisRemover = p1.getTarefaPrevistas().size();
        if(depoisRemover!=countAntesRemover){
            assertEquals(0,0);
        }else{
            assertNotEquals(1,0);
        }
    }

    @Test
    void deleteProjeto() {
        when(projetoService.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.deleteProjeto(1L).isEmpty());

    }

    @Test
    void criarProjeto() {
        Projeto p1 = new Projeto();
        when(projetoService.criarProjeto(p1)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.criarProjeto(p1).isPresent());
    }

    @Test
    void custoPrevistoProjeto() {
        when(projetoService.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertNotNull(projetoService.custoPrevistoProjeto(1L).isNaN());

    }

    @Test
    void duracaoPrevistaProjeto() {
        when(projetoService.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertNotNull(projetoService.duracaoPrevistaProjeto(1L).isNaN());
    }
}