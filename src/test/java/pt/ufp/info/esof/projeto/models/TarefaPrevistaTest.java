package pt.ufp.info.esof.projeto.models;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TarefaPrevistaTest {

    @Test
    void custoPrevistoTarefa() {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setTempoPrevistoHoras(30);
        tp1.atribuirTarefaEfetiva();
        Empregado e1 = new Empregado();
        e1.setCargo(Cargo.analistaSenior); //80 euros/h
        tp1.getTarefaEfetiva().adicionarEmpregado(e1);

        float custo  = 30*80;
        assertEquals(custo,tp1.custoPrevistoTarefa());

    }
}