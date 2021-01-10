package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Projeto;

import pt.ufp.info.esof.projeto.services.projetocases.facades.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest (ProjetoServiceFacades.class)
class ProjetoServiceImplTest {

    @MockBean
    private ProjetoService projetoService;

    @MockBean
    private deleteProjetoUseCase deleteProjetoUseCase;
    @MockBean
    private CriarProjetoUseCase criarProjetoUseCase;

    @MockBean
    private ListaProjetoPorIdUseCase listaProjetoPorIdUseCase;
    @MockBean
    private ListTodosProjetosUseCase listTodosProjetosUseCase;

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
        when(deleteProjetoUseCase.deleteProjeto(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.deleteProjeto(1L).isEmpty());

    }

    @Test
    void criarProjeto() {
        Projeto p1 = new Projeto();
        when(criarProjetoUseCase.criarProjeto(p1)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.criarProjeto(p1).isPresent());
    }


}