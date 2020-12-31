package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TarefaEfetiva {
  public static void main(String[] args) {
    TarefaEfetiva t1 = new TarefaEfetiva();
    t1.concluirTarefa();
  }
  private long id;
  private String nome;
  private Empregado empregado;
  private float duracaoHoras;
  private TarefaPrevista tarefaPrevista;

  private enum EstadosDaTarefa {
    Concluido,
    EmAndamento,
    Atrasado,
    NaoComecado
  }
  private EstadosDaTarefa estadoTarefa = EstadosDaTarefa.NaoComecado;

  public void atribuirEstadoTarefa(){
    if(percentagemConclusao() == 0){
        estadoTarefa = EstadosDaTarefa.NaoComecado;
    }else if(percentagemConclusao() >= 1 && percentagemConclusao() <= 99){
        estadoTarefa = EstadosDaTarefa.EmAndamento;
    }else if(estadoTarefa != EstadosDaTarefa.Concluido){
          estadoTarefa = EstadosDaTarefa.Atrasado;
    }
  }

  public float custoEfetivoTarefa(){
    return duracaoHoras * empregado.valorHora();
  }

  public int percentagemConclusao(){
    return (int)(duracaoHoras/(tarefaPrevista.getTempoPrevistoHoras())*100);
  }

  public EstadosDaTarefa concluirTarefa(){
    estadoTarefa =  EstadosDaTarefa.Concluido;
    return estadoTarefa;
  }

}