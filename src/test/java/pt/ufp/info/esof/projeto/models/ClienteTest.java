package pt.ufp.info.esof.projeto.models;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pt.ufp.info.esof.projeto.models.Cargo.desenvolvedorJunior;
import static pt.ufp.info.esof.projeto.models.Cargo.desenvolvedorSenior;

class ClienteTest {

    @Test
    void consultarCustoProjeto() {
        Cliente c1 = new Cliente();
        Projeto p1 = new Projeto();
        c1.getProjetos().add(p1);
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaEfetiva();
        t2.atribuirTarefaEfetiva();


        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);

        float custo= 40*10+15*10;
        assertEquals(custo,c1.consultarCustoProjeto(p1));
    }

    @Test
    void consultarPrazoProjeto() {
        Cliente c1 = new Cliente();
        Projeto p1 = new Projeto();
        c1.getProjetos().add(p1);
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaEfetiva();
        t2.atribuirTarefaEfetiva();


        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);

        float prazo=10+15;//25h prazo
        assertEquals(prazo,c1.consultarPrazoProjeto(p1));

    }

    @Test
    void consultarEstadoProjeto() {
        Cliente c1 = new Cliente();
        Projeto p1 = new Projeto();
        // Para a condicao de quando o aluno nao "possui" aquele projeto
        assertNull(c1.consultarEstadoProjeto(p1));
        c1.getProjetos().add(p1);
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        p1.adicionarTarefas(t1);
        p1.adicionarTarefas(t2);

        t1.setTempoPrevistoHoras(10);
        t2.setTempoPrevistoHoras(15);

        t1.atribuirTarefaEfetiva();
        t2.atribuirTarefaEfetiva();


        Empregado e1 = new Empregado();
        Empregado e2 = new Empregado();
        e1.setCargo(desenvolvedorSenior);  //40 euros por hora
        e2.setCargo(desenvolvedorJunior);  //10 euros por hora

        t1.getTarefaEfetiva().adicionarEmpregado(e1);
        t2.getTarefaEfetiva().adicionarEmpregado(e2);

        t1.getTarefaEfetiva().setDuracaoHoras(5);  // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(19);  // o empregado 2 trabalhou 10h
        assertEquals(Estados.EmAndamento,c1.consultarEstadoProjeto(p1));

        t1.getTarefaEfetiva().setDuracaoHoras(0);  // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(0);  // o empregado 2 trabalhou 10h
        assertEquals(Estados.NaoComecado,c1.consultarEstadoProjeto(p1));

        t1.getTarefaEfetiva().setDuracaoHoras(20);  // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(30);  // o empregado 2 trabalhou 10h
        assertEquals(Estados.Atrasado,c1.consultarEstadoProjeto(p1));

        t1.getTarefaEfetiva().setDuracaoHoras(20);  // o empregado 1 trabalhou 10h
        t2.getTarefaEfetiva().setDuracaoHoras(30);  // o empregado 2 trabalhou 10h
        t1.getTarefaEfetiva().concluirTarefa();
        t2.getTarefaEfetiva().concluirTarefa();
        assertEquals(Estados.Concluido,c1.consultarEstadoProjeto(p1));



    }
}