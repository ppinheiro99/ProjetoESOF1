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



//  public float consultarCustoProjeto(){
//   /* int i = 0;
//    while (i<projetos.size()){
//      System.out.println(projetos.get(i).custoPrevistoProjeto());
//      i++;
//    }*/
//    int custo=0;
//    for(Projeto projeto:projetos){
//      custo+=projeto.custoPrevistoProjeto();
//    }
//    return custo;
//
//    //return projetos.stream().map(Projeto::custoPrevistoProjeto).reduce((aFloat, aFloat2) -> aFloat+aFloat2).orElse(0f);
//  }

//  public void consultarPrazoProjeto(){
//    int i = 0;
//    while (i<projetos.size()){
//      System.out.println(projetos.get(i).duracaoPrevistaHoras() + "\n");
//      i++;
//    }
//  }

//  public void consultarEstadoProjeto(){
//    int i = 0;
//    while (i<projetos.size()){
//      System.out.println(projetos.get(i).estadoDoProjeto());
//      i++;
//    }
//  }

}