package pt.ufp.info.esof.projeto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefasDTO {
    private String nome;
    private String empregadoNome;
    private String projeto;
    private float tempoPrevisto;
    private float tempoEfetivo;
    private boolean concluida;
}
