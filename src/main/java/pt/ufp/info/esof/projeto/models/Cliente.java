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
  private String email;
  private List<Projeto> projetos = new ArrayList<>();

  public float consultarCustoProjeto(Projeto projeto){
    float custo=0;
    if(projetos.contains(projeto))
      custo=projeto.custoPrevistoProjeto();

    return custo;
  }

  public float consultarPrazoProjeto(Projeto projeto){
    float prazo = 0;
    if(projetos.contains(projeto))
      prazo =projeto.duracaoPrevistaHoras();

    return prazo;
  }

  public Estados consultarEstadoProjeto(Projeto p){
    if(this.getProjetos().contains(p)){
      return this.getProjetos().get((int)p.getId()).estadoDoProjeto();
    }
    System.out.println("Este cliente nao tem este projeto");
    return null;
  }
}