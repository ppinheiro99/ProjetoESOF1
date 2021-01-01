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

    public Estados estadoDoProjeto() {
        Estados estadoProjeto = Estados.NaoComecado;
        if(duracaoEfetivaHoras() == 0){ // se ainda nao tiver horas no projeto é porque este ainda nao comecou
            return estadoProjeto;
        }

        if(duracaoEfetivaHoras() >= duracaoPrevistaHoras()){
            estadoProjeto = Estados.Concluido; // se o numero de horas efetivas for igual ou superior ás previstas, calculamos que ja terminaram todas as tarefas
        }

        for (TarefaPrevista t : this.tarefaPrevistas) { // percorremos as tarefas para verificar se todas já foram concluidas
            if (t.getTarefaEfetiva().getEstadoTarefa() != Estados.Concluido){
                if(estadoProjeto == Estados.Concluido) { // se o estado de alguma tarefa for nao concluido quer dizer que o projeto ainda nao terminou mas como ja passou do tempo previsto, o projeto encontra-se atrasado
                    return Estados.EmAndamento;
                }
                    return Estados.Atrasado;
            }
        }
        return Estados.Concluido;
    }

    public void mostrarProgresso(){
        System.out.println(this);
        System.out.println("Projeto "+this.estadoDoProjeto()+" com "+this.percentagemConclusao()+"%");

//        tarefas.stream().map(Tarefa::getNome).collect(Collectors.toList());
//        for (Tarefa t:this.getTarefas()) {
//            System.out.println(t);
//        }
    }
}
