package pt.ufp.info.esof.projeto.services;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.*;

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
        projetos.add(new Projeto());
        when(projetoService.findAll()).thenReturn((List<Projeto>) projetos);
        projetoService.findAll();
    }


/*
    @Test
    void findById() {
        when(listaProjetoPorIdUseCase.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(listaProjetoPorIdUseCase.findById(1L).isPresent());
    }

    @Test
    void deleteProjeto() {
        Projeto p = new Projeto();
        p.setId(1L);
        when(eliminarProjetoUseCase.deleteProjeto(p.getId())).thenReturn(Optional.of(p));
        assertTrue(eliminarProjetoUseCase.deleteProjeto(p.getId()).isPresent());
    }

    @Test
    void criarProjeto() {
        Projeto p1 = new Projeto();
        p1.setId(1L);
        when(criarProjetoUseCase.criarProjeto(p1)).thenReturn(Optional.of(p1));
        assertTrue(criarProjetoUseCase.criarProjeto(p1).isPresent());
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

        when(custoPrevistoProjeto.custoPrevistoProjeto(projeto.getId())).thenReturn(valor);
        assertEquals(valor,projetoService.custoPrevistoProjeto(projeto.getId()));

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
        when(searchProjetoUseCase.pesquisarProjeto(query)).thenReturn(projetos);
        assertEquals(projetoService.searchProjeto(query),projetos);
    }*/
}