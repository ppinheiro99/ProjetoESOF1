package pt.ufp.info.esof.projeto.services.tarefacases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class AtribuiTarefaEmpregado {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final EmpregadoRepository empregadoRepository;

    public AtribuiTarefaEmpregado(TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository, EmpregadoRepository empregadoRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.empregadoRepository = empregadoRepository;
    }

    public TarefaEfetiva convertTarefaPevistaEfetiva(TarefaPrevista tp1){
        TarefaEfetiva t1 = new TarefaEfetiva();
        t1.setId(tp1.getId());
        t1.setNome(tp1.getNome());
        t1.setTarefaPrevista(tp1);
        return t1;
    }

    public Optional<Empregado> atribuiTarefasEmpregados(String emailEmpregado, Long idTarefa) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findByEmail(emailEmpregado);
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(idTarefa);
        if(optionalEmpregado.isPresent() && optionalTarefaPrevista.isPresent() ){
            TarefaPrevista tp1 = optionalTarefaPrevista.get();
            if(!optionalEmpregado.get().getTarefaEfetivas().contains(tp1)) {
                TarefaEfetiva t1 = convertTarefaPevistaEfetiva(tp1); // converve a tp1 para tarefa prevista
                t1.setEmpregado(optionalEmpregado.get()); // associa o empregado à tarefa
                tarefaEfetivaRepository.save(t1); // adiconar tarefa à  BD mal esta seja associada ao empregago
                optionalEmpregado.get().getTarefaEfetivas().add(t1); // so pode associar depois da tefetiva estiver na BD
                tp1.setTarefaEfetiva(t1);

                return optionalEmpregado;
            }
        }
        return Optional.empty();
    }
}
