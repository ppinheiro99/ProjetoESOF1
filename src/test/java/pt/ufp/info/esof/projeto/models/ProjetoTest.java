package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void adicionarTarefas() {
        Projeto p1 = new Projeto();
        TarefaPrevista t1 = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();

        assertEquals(0,p1.getTarefaPrevistas().size());
        p1.adicionarTarefas(t1);
        assertEquals(1,p1.getTarefaPrevistas().size());
        // Nao pode adicionar porque a tarefa já foi criada
        p1.adicionarTarefas(t1);
        assertEquals(1,p1.getTarefaPrevistas().size());
        p1.adicionarTarefas(t2);
        assertEquals(2,p1.getTarefaPrevistas().size());
    }

    @Test
    void custoPrevistoProjeto() {
    }

    @Test
    void custoEfetivoProjeto() {
    }

    @Test
    void duracaoPrevistaHoras() {
    }

    @Test
    void duracaoEfetivaHoras() {
    }

    @Test
    void percentagemConclusao() {
    }

    @Test
    void estadoDoProjeto() {
    }

    @Test
    void mostrarProgresso() {
    }
}