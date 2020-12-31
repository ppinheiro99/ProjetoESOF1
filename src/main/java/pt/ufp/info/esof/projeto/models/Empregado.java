package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Empregado {
    private long id;
    private String nome;
    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;

    public int valorHora(){
        return cargo.valorHora;
    }
}