package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TarefaPrevista {
    private long id;
    private String nome;
    private float tempoPrevistoHoras;
    private TarefaEfetiva tarefaEfetiva;
    private Projeto projeto;

    public float custoPrevistoTarefa(){
        return tarefaEfetiva.getEmpregado().valorHora()*tempoPrevistoHoras;
    }
}
