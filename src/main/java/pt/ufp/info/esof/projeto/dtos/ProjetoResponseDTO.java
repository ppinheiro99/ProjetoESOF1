package pt.ufp.info.esof.projeto.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoResponseDTO {
    private String nome;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();
    private String emailCliente;
    private float custo;
    private float tempoHoras;
}
