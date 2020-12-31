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
    private List<TarefaPrevista> tarefaPrevistas = new ArrayList<>();
    private Cliente cliente;

    public void adicionarTarefas(TarefaPrevista t) {
        if (!tarefaPrevistas.contains(t)) {
            tarefaPrevistas.add(t);
            t.setProjeto(this);
        }
    }

    public float custoPrevistoProjeto() {
        float custo = 0;
        for (TarefaPrevista t : this.tarefaPrevistas) {
            custo = custo + t.custoPrevistoTarefa();
        }
        return custo;
    }

    public float custoEfetivoProjeto() {
        float custo = 0;
        for (TarefaPrevista t : this.tarefaPrevistas) {
            custo = custo + t.custoPrevistoTarefa();
        }
        return custo;
    }

    public float duracaoPrevistaHoras() {
        float duracao = 0;
        for (TarefaPrevista t : this.tarefaPrevistas) {
            duracao = duracao + t.getTempoPrevistoHoras();
        }
        return duracao;
    }

    public float duracaoEfetivaHoras() {
        float duracao = 0;
        for (TarefaPrevista t : this.tarefaPrevistas) {
            duracao = duracao + t.getTarefaEfetiva().getDuracaoHoras();
        }
        return duracao;
    }

    public int percentagemConclusao() {
        return (int) (( this.duracaoEfetivaHoras()/this.duracaoPrevistaHoras()) * 100);
    }

//    public EstadosProjeto estadoDoProjeto() {
//        for (TarefaPrevista t : this.tarefaPrevistas) {
//            if (t.getTarefaEfetiva().getEstadoTarefa() == "NaoConcluida") {
//                return EstadosProjeto.NaoConluido;
//            }
//        }
//        return EstadosProjeto.Concluido;
//    }

//    public void mostrarProgresso(){
//        System.out.println(this);
//        System.out.println("Projeto "+this.estadoDoProjeto()+" com "+this.percentagemConclusao()+"%");
//
////        tarefas.stream().map(Tarefa::getNome).collect(Collectors.toList());
//        for (Tarefa t:this.getTarefas()) {
//            System.out.println(t);
//        }
//    }
}
