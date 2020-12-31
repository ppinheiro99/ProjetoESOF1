package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Projeto {
    private long id;
    private String nome;

    private enum EstadosProjeto {
        Concluido,
        NaoConluido,
    }
    private List<Tarefa> tarefas = new ArrayList<>();
    private Cliente cliente;

    public void adicionarTarefas(Tarefa t) {
        if (!tarefas.contains(t)) {
            tarefas.add(t);
            t.setProjeto(this);
        }
    }

    public float custoPrevistoProjeto() {
        float custo = 0;
        for (Tarefa t : this.tarefas) {
            custo = custo + t.custoPrevistoTarefa();
        }
        return custo;
    }

    public float custoEfetivoProjeto() {
        float custo = 0;
        for (Tarefa t : this.tarefas) {
            custo = custo + t.custoEfetivoTarefa();
        }
        return custo;
    }

    public float duracaoPrevistaHoras() {
        float duracao = 0;
        for (Tarefa t : this.tarefas) {
            duracao = duracao + t.getTempoPrevisto().getTempoPrevistoHoras();
        }
        return duracao;
    }

    public float duracaoEfetivaHoras() {
        float duracao = 0;
        for (Tarefa t : this.tarefas) {
            duracao = duracao + t.getTempoEfetivo().getTempoEfetivoHoras();
        }
        return duracao;
    }

    public int percentagemConclusao() {
        return (int) (( this.duracaoEfetivaHoras()/this.duracaoPrevistaHoras()) * 100);
    }

    public EstadosProjeto estadoDoProjeto() {
        for (Tarefa t : this.tarefas) {
            if (!t.registaConclusaoTarefa()) {
                return EstadosProjeto.NaoConluido;
            }
        }
        return EstadosProjeto.Concluido;
    }

    public void mostrarProgresso(){
        System.out.println(this);
        System.out.println("Projeto "+this.estadoDoProjeto()+" com "+this.percentagemConclusao()+"%");

//        tarefas.stream().map(Tarefa::getNome).collect(Collectors.toList());
        for (Tarefa t:this.getTarefas()) {
            System.out.println(t);
        }
    }
}
