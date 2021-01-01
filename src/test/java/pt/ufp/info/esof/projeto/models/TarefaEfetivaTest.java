package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TarefaEfetivaTest {

    @Test
    void estadoDaTarefa() {
        TarefaEfetiva te1 = new TarefaEfetiva();
        TarefaPrevista tp1 = new TarefaPrevista();

        te1.setTarefaPrevista(tp1);
        tp1.setTarefaEfetiva(te1);

        // Concluída
        tp1.setTempoPrevistoHoras(8);
        te1.setDuracaoHoras(8);
        assertEquals(Estados.Concluido,te1.estadoDaTarefa());
        //Atrasada
        tp1.setTempoPrevistoHoras(8);
        te1.setDuracaoHoras(10);
        assertEquals(Estados.Atrasado,te1.estadoDaTarefa());
        //Nao comecou
        tp1.setTempoPrevistoHoras(8);
        te1.setDuracaoHoras(0);
        assertEquals(Estados.NaoComecado,te1.estadoDaTarefa());
        //Em andamento
        tp1.setTempoPrevistoHoras(8);
        te1.setDuracaoHoras(4);
        assertEquals(Estados.EmAndamento,te1.estadoDaTarefa());
        // Concluida com atraso
        tp1.setTempoPrevistoHoras(8);
        te1.setDuracaoHoras(10);
        te1.definirEstadoTarefa(Estados.Concluido);
        assertEquals(Estados.ConcluidoComAtraso,te1.estadoDaTarefa());
    }

    @Test
    void custoEfetivoTarefa() {
    }

    @Test
    void percentagemConclusao() {
    }

    @Test
    void definirEstadoTarefa(){
        TarefaEfetiva te1 = new TarefaEfetiva();
        // Concluido
        assertEquals(Estados.Concluido,te1.definirEstadoTarefa(Estados.Concluido));
        //Atrasada
        assertEquals(Estados.Atrasado,te1.definirEstadoTarefa(Estados.Atrasado));
        //Nao comecou
        assertEquals(Estados.NaoComecado,te1.definirEstadoTarefa(Estados.NaoComecado));
        //Em andamento
        assertEquals(Estados.EmAndamento,te1.definirEstadoTarefa(Estados.EmAndamento));
        // Concluida com atraso
        assertEquals(Estados.Atrasado,te1.definirEstadoTarefa(Estados.Atrasado));

    }

}