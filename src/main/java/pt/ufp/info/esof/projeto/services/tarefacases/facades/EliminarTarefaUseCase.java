package pt.ufp.info.esof.projeto.services.tarefacases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class EliminarTarefaUseCase {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    public EliminarTarefaUseCase(TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }


    public Optional<TarefaPrevista> deleteTarefa(Long idTarefa) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(idTarefa);
        if(optionalTarefaPrevista.isPresent()){
            TarefaPrevista tp1 = optionalTarefaPrevista.get();
            tp1.getProjeto().getTarefaPrevistas().remove(tp1); // removo a conexao com o projeto e vice-versa
            tp1.setProjeto(null);
            if(tp1.getTarefaEfetiva() != null){ // se tiver tarefas efetivas, é porque já tem um empregado associado à tarefa, temos de cortar essas conexoes
                TarefaEfetiva te1 = tp1.getTarefaEfetiva();
                te1.setTarefaPrevista(null); // removo a ligacao da tarefaEfetiva com a Tarefa prevista e vice-versa
                tp1.setTarefaEfetiva(null);
                te1.getEmpregado().getTarefaEfetivas().remove(te1); // removo a tarefa Efetiva do empregado e vice versa
                te1.setEmpregado(null);
                tarefaEfetivaRepository.delete(te1); // como a efetiva depende da prevista ( e feita atraves dela ) quando a prevista é eliminada a efetiva tb tem de ser
            }
            tarefaPrevistaRepository.delete(tp1);
        }
        return Optional.empty();
    }
}
