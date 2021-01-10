package pt.ufp.info.esof.projeto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaEfetivaResponseDTO {
    private String nome;
    private long idEmpregado;
    private long idTarefaPrevista;
    private float duracaoHoras;
}
