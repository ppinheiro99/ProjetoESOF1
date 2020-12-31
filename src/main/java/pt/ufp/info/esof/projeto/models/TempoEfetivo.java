package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TempoEfetivo {
    private long id;
    private float tempoEfetivoHoras;
    private Tarefa tarefa;

    public void registaPeriodosDeTempo(float tempoHoras){ // empregado regista os tempos que dedicou à tarefa
        tempoEfetivoHoras = tempoHoras + tempoEfetivoHoras;
    }
}
