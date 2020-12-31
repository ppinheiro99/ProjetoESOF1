package pt.ufp.info.esof.projeto.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {
  private long id;
  private String nome;
  private List<Projeto> projetos = new ArrayList<>();

  public void consultarCustoProjeto(){
    int i = 0;
    while (i<projetos.size()){
      System.out.println(projetos.get(i).custoPrevistoProjeto());
      i++;
    }
  }

  public void consultarPrazoProjeto(){
    int i = 0;
    while (i<projetos.size()){
      System.out.println(projetos.get(i).duracaoPrevistaHoras() + "\n");
      i++;
    }
  }

  public void consultarEstadoProjeto(){
    int i = 0;
    while (i<projetos.size()){
      System.out.println(projetos.get(i).estadoDoProjeto());
      i++;
    }
  }

}