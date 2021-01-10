package pt.ufp.info.esof.projeto.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {
    private String nome;
    private float tempoPrevistoHoras;
    private long idProjeto;

}
