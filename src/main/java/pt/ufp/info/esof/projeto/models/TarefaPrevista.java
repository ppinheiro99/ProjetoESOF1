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
        if( tarefaEfetiva != null) { // caso a tarefa ainda nao tenha empregado nao se pode fazer o custo pq nao sabemos qual é o empregado para sabermos o seu custo hora
            return tarefaEfetiva.getEmpregado().valorHora() * tempoPrevistoHoras;
        }
        return 0;
    }


    public void atribuirTarefaPrevista(){
        this.tarefaEfetiva = new TarefaEfetiva();
        this.tarefaEfetiva.setId(this.getId());
        this.tarefaEfetiva.setNome(this.getNome());
        this.tarefaEfetiva.setTarefaPrevista(this);
        //this.setTarefaEfetiva(t);


    }

}
