package pt.ufp.info.esof.projeto.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TarefaEfetiva {
    private long id;
    private String nome;
    private Empregado empregado;
    private float duracaoHoras;
    private TarefaPrevista tarefaPrevista;
    private Estados estadoTarefa = Estados.NaoComecado;

    public Estados estadoDaTarefa() { // apenas diz o estado do projeto com base no tempo efetivo e previsto, para
        if (percentagemConclusao() == 0) {
            return Estados.NaoComecado;
        } else if (percentagemConclusao() >= 1 && percentagemConclusao() <= 99 && estadoTarefa != Estados.Concluido) {
            return Estados.EmAndamento;
        } else if (percentagemConclusao() > 100 && estadoTarefa != Estados.Concluido) {
            return Estados.Atrasado;
        } else if (percentagemConclusao() > 100 && estadoTarefa == Estados.Concluido) {
            return Estados.ConcluidoComAtraso;
        }
        return Estados.Concluido;
    }

    public float custoEfetivoTarefa() {
        return duracaoHoras * empregado.valorHora();
    }

    public float duracaoEfetivaHoras(float duracao) {
        return duracaoHoras = duracao;
    }

    public int percentagemConclusao() {
        return (int) (duracaoHoras / (tarefaPrevista.getTempoPrevistoHoras()) * 100);
    }

    public Estados definirEstadoTarefa(Estados estado) {
        estadoTarefa = estado;
        return estadoTarefa;
    }

    public void adicionarEmpregado(Empregado empregado) {
        this.setEmpregado(empregado);
        empregado.getTarefaEfetivas().add(this);
    }

}