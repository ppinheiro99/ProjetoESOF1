package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProjetoServiceImplTest {
    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;


    @Test
    void findAll() {
        ArrayList<Projeto> projetos = new ArrayList<>();
        when(projetoRepository.findAll()).thenReturn((List<Projeto>) projetos);
        assertNotNull(projetoRepository.findAll());

    }



    @Test
    void findById() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoRepository.findById(1L).isPresent());

    }

    @Test
    void deleteProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        Cliente c = new Cliente();
        c.getProjetos().add(projeto);
        projeto.setCliente(c);
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        projetoService.deleteProjeto(1L);


    }

    @Test
    void criarProjeto() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));

        assertEquals(projetoRepository.findById(1L),Optional.of(new Projeto()));


    }
/*
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

        Float valor = tarefa.custoPrevistoTarefa() ;

        projeto.getTarefaPrevistas().add(tarefa);
        tarefa.setProjeto(projeto);

       // when(projetoService.custoPrevistoProjeto(1L)).thenReturn(valor);

        assertNotEquals(valor,projetoService.custoPrevistoProjeto(1L));

    }

    @Test
    void duracaoPrevistaProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);
        tarefa.setTempoPrevistoHoras(8);
        float valor = 0 ;
        projeto.getTarefaPrevistas().add(tarefa);

        when(duracaoPrevistaProjeto.duracaoPrevistaProjeto(projeto.getId())).thenReturn(valor);
        assertEquals(valor,projetoService.duracaoPrevistaProjeto(projeto.getId()));
    }

    @Test
    void assocTarefasProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setId(1L);

        when(associarTarefasAoProjetoUseCase.assocTarefasProjeto(projeto.getId(),tarefa.getId())).thenReturn(Optional.of(projeto));

        assertTrue(projetoService.assocTarefasProjeto(1L,1L).isPresent());
        assertTrue(projetoService.assocTarefasProjeto(1L,10L).isEmpty());
    }
    @Test
    void pesquisarProjeto(){
        Projeto p = new Projeto();
        p.setNome("ESOF");
        p.setId(1L);
        Cliente c = new Cliente();
        c.setNome("Cliente2");
        c.setId(1L);
        c.setEmail("cliente2@teste.com");

        Map<String, String> query = new HashMap<>();
        query.put("nome",p.getNome());


        p.setCliente(c);
        ArrayList<Projeto>projetos = new ArrayList<>();
        projetos.add(p);
        when(projetoService.searchProjeto(query)).thenReturn(projetos);
        assertEquals(projetoService.searchProjeto(query),projetos);


    }*/
}