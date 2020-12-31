package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Tarefa {
  private long id;
  private String nome;
  private Empregado empregado;
  private Projeto projeto;
  private TempoEfetivo tempoEfetivo;
  private TempoPrevisto tempoPrevisto;

  private boolean concluida;


  public boolean registaConclusaoTarefa(){
    return tempoEfetivo.getTempoEfetivoHoras() == tempoPrevisto.getTempoPrevistoHoras();
  }
  public float custoPrevistoTarefa(){
    return empregado.valorHora()*tempoPrevisto.getTempoPrevistoHoras();
  }
  public float custoEfetivoTarefa(){
    return tempoPrevisto.getTempoPrevistoHoras() * empregado.valorHora();
  }
  public int percentagemConclusao(){return (int)(tempoEfetivo.getTempoEfetivoHoras()/(tempoPrevisto.getTempoPrevistoHoras())*100);}


  public void conluir_tarefa(){this.concluida=true;}








}