package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.projetocases.facades.*;
import pt.ufp.info.esof.projeto.services.projetocases.facades.ProjetoServiceFacades;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProjetoServiceImplTest {
    @Autowired
    private ProjetoService projetoService;
    @MockBean
    private EliminarProjetoUseCase eliminarProjetoUseCase;
    @MockBean
    private CriarProjetoUseCase criarProjetoUseCase;
    @MockBean
    private ListaProjetoPorIdUseCase listaProjetoPorIdUseCase;
    @MockBean
    private ListTodosProjetosUseCase listTodosProjetosUseCase;
    @MockBean
    private  CustoPrevistoProjeto custoPrevistoProjeto;
    @MockBean
    private  DuracaoPrevistaProjeto duracaoPrevistaProjeto;
    @MockBean
    private  AssociarTarefasAoProjetoUseCase associarTarefasAoProjetoUseCase;

    @Test
    void findAll() {
        when(listTodosProjetosUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById() {
        when(listaProjetoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertTrue(projetoService.findById(2L).isEmpty());
    }

    @Test
    void deleteProjeto() {
        Projeto p = new Projeto();
        p.setId(1L);
        when(eliminarProjetoUseCase.deleteProjeto(p.getId())).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.deleteProjeto(p.getId()).isPresent());
    }

    @Test
    void criarProjeto() {
        Projeto p1 = new Projeto();
        p1.setId(1L);
        when(criarProjetoUseCase.criarProjeto(p1)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.criarProjeto(p1).isPresent());
    }

    @Test
    void custoPrevistoProjeto() {
        when(listaProjetoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertNotNull(custoPrevistoProjeto.custoPrevistoProjeto(1L).isNaN());

    }

    @Test
    void duracaoPrevistaProjeto() {
        when(listaProjetoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertNotNull(duracaoPrevistaProjeto.duracaoPrevistaProjeto(1L).isNaN());
    }

    @Test
    void assocTarefasProjeto() {
        assertTrue(associarTarefasAoProjetoUseCase.assocTarefasProjeto(1L,2L).isPresent());
        assertTrue(associarTarefasAoProjetoUseCase.assocTarefasProjeto(1L,10L).isEmpty());
    }


}