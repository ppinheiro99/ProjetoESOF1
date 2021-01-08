package pt.ufp.info.esof.projeto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Cargo;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoResponseDTO {
    private String nome;
    private String email;
    private Cargo cargo;
    private List<String> tarefasEfetivas = new ArrayList<>();
}
