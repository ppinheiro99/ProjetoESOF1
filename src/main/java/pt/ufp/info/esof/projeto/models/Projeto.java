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
    private Estados estadoProjeto = Estados.NaoComecado;  //INICIALMENTE FICA A NÃO COMEÇADO
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
            if (t.getTarefaEfetiva() != null)
                custo = custo + t.getTarefaEfetiva().custoEfetivoTarefa();
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
            if (t.getTarefaEfetiva() != null){
                duracao = duracao + t.getTarefaEfetiva().getDuracaoHoras();
            }
        }
        return duracao;
    }


    public int percentagemConclusao() {
        return (int) ((this.duracaoEfetivaHoras() / this.duracaoPrevistaHoras()) * 100);
    }

    public Estados estadoDoProjeto() {
        if (duracaoEfetivaHoras() == 0) { // se ainda nao tiver horas no projeto é porque este ainda nao comecou
            return Estados.NaoComecado;
        }

        if (duracaoEfetivaHoras() < duracaoPrevistaHoras()) { // se o numero de horas efetivas for igual ou superior ás previstas, calculamos que ja terminaram todas as tarefas
            for (TarefaPrevista t : this.tarefaPrevistas) { // percorremos as tarefas para verificar se todas já foram concluidas
                if (t.getTarefaEfetiva().getEstadoTarefa() != Estados.Concluido) {
                    return Estados.EmAndamento;
                }
            }
            return Estados.Concluido; // caso tenha acabado antes do tempo
        } else { // >=
            if (estadoProjeto != Estados.Concluido)
                for (TarefaPrevista t : this.tarefaPrevistas) { // percorremos as tarefas para verificar se todas já foram concluidas
                    if (t.getTarefaEfetiva().getEstadoTarefa() != Estados.Concluido) {
                        return Estados.Atrasado; // se não tiver sido conmcluido mais cedo
                    }
                }
            return Estados.Concluido;
        }
    }
    
}
