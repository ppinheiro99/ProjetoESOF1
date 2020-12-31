package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TempoPrevisto {
    private long id;
    private float tempoPrevistoHoras;
    private Tarefa tarefa;
}
