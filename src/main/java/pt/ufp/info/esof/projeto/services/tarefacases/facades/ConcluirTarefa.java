package pt.ufp.info.esof.projeto.services.tarefacases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class ConcluirTarefa {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;

    public ConcluirTarefa(TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository, EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
    }

    public Optional<TarefaEfetiva> terminarTarefa(Long idTarefa) {
        Optional<TarefaEfetiva> optionalTarefaEfetiva = tarefaEfetivaRepository.findById(idTarefa);
        if(optionalTarefaEfetiva.isPresent()){
            TarefaEfetiva te1 = optionalTarefaEfetiva.get();
            if(te1.getEmpregado()!= null && te1.getDuracaoHoras() > 0){ // se ele tiver um empregado associado e o empregado ja comecou o projeto entao pode colocar como concluido
                te1.concluirTarefa();
                projetoRepository.save(te1.getTarefaPrevista().getProjeto());
                return optionalTarefaEfetiva;
            }
        }
        return Optional.empty();
    }
}
