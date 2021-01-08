package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pt.ufp.info.esof.projeto.models.Cargo.*;

import pt.ufp.info.esof.projeto.services.TarefaServiceImpl;

class ProjetoTest {

    @Test
    void adicionarTarefas() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();

        assertEquals(0, p1.getTarefaPrevistas().size());
        p1.adicionarTarefas(t1);
        assertEquals(1, p1.getTarefaPrevistas().size());
        // Nao pode adicionar porque a tarefa já foi criada
        p1.adicionarTarefas(t1);
        assertEquals(1, p1.getTarefaPrevistas().size());
        p1.adicionarTarefas(t2);
        assertEquals(2, p1.getTarefaPrevistas().size());
    }

    @Test
    void custoPrevistoProjeto() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();


        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        float custo = 40 * 10 + 15 * 10;

        assertEquals(custo, p1.custoPrevistoProjeto());


    }

    @Test
    void custoEfetivoProjeto() {

        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();


        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        t1.getTarefaEfetiva().setDuracaoHoras(5);  // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(20);  // o empregado 2 trabalhou 10h

        float custo = 40 * 5 + 10 * 20;

        assertEquals(custo, p1.custoEfetivoProjeto());

    }

    @Test
    void duracaoPrevistaHoras() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();

        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        t1.setTempoPrevistoHoras(5);  // o empregado 1 trabalhou 10h
        t2.setTempoPrevistoHoras(20);  // o empregado 1 trabalhou 10h

        float duracao = 5 + 20;

        assertEquals(duracao, p1.duracaoPrevistaHoras());

    }

    @Test
    void duracaoEfetivaHoras() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();

        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        t1.getTarefaEfetiva().setDuracaoHoras(10); // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(20);  // o empregado 1 trabalhou 10h

        float duracao = 10 + 20;

        assertEquals(duracao, p1.duracaoEfetivaHoras());
    }

    @Test
    void percentagemConclusao() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);


        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();

        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        t1.getTarefaEfetiva().setDuracaoHoras(5); // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(7);  // o empregado 1 trabalhou 10h

        float percentagem = ((7f + 5f) / (10f + 15f)) * 100;


        assertEquals((int) percentagem, p1.percentagemConclusao());

    }

    @Test
    void estadoDoProjeto() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaPrevista();
        t2.atribuirTarefaPrevista();

        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);


        t1.getTarefaEfetiva().setDuracaoHoras(5); // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(7);  // o empregado 1 trabalhou 10h

        assertEquals(Estados.EmAndamento, p1.estadoDoProjeto());

    }

    @Test
    void mostrarProgresso() {
      //não ha teste para este metodo que não retorna nada
      // Apenas aapresenta informação relativa ão projeto
    }
}