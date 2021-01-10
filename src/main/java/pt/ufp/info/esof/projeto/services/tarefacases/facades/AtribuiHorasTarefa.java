package pt.ufp.info.esof.projeto.services.tarefacases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;

import java.util.Optional;

@Service
public class AtribuiHorasTarefa {
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final ProjetoRepository projetoRepository;

    public AtribuiHorasTarefa(TarefaEfetivaRepository tarefaEfetivaRepository, ProjetoRepository projetoRepository) {
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.projetoRepository = projetoRepository;
    }

    public Optional<TarefaEfetiva> atribuiHoras(Long idTarefa, Float duracaoHoras) {
        Optional<TarefaEfetiva> optionalTarefaEfetiva = tarefaEfetivaRepository.findById(idTarefa);
        if(optionalTarefaEfetiva.isPresent()){
            TarefaEfetiva te = optionalTarefaEfetiva.get();
            te.duracaoEfetivaHoras(duracaoHoras); // atribui horas à tarefa
            projetoRepository.save(te.getTarefaPrevista().getProjeto());
            return optionalTarefaEfetiva;
        }
        return Optional.empty();
    }
}
