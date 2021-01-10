package pt.ufp.info.esof.projeto.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Estados;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoResponseDTO {
    private String nome;
    private List<String> tarefas = new ArrayList<>();
    private String emailCliente;
    private Estados estadoProjeto;
    private float custo;
    private float custoEfetivo;
    private float tempoHoras;
}
