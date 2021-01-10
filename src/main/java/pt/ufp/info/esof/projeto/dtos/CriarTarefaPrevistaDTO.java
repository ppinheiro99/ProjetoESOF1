package pt.ufp.info.esof.projeto.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarTarefaPrevistaDTO implements CreateDTO<TarefaPrevista> {
    private String nome;
    private float duracaoHoras;
    private Long projetoID;

    @Override
    public TarefaPrevista converter (){
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome(nome);
        tarefaPrevista.setTempoPrevistoHoras(duracaoHoras);
        Projeto p = new Projeto();
        p.setId(projetoID);
        tarefaPrevista.setProjeto(p);

        return tarefaPrevista;
    }
}