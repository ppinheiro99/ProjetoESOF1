package pt.ufp.info.esof.projeto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TarefaCreateDTO implements CreateDTO<TarefaPrevista> {
    private String nome;
    private float tempoPrevistoHoras;

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome(nome);
        tarefaPrevista.setTempoPrevistoHoras(tempoPrevistoHoras);
        return tarefaPrevista;
    }
}



