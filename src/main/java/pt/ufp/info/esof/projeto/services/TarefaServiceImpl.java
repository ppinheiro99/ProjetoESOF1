package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class TarefaServiceImpl implements TarefaService{
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    public TarefaServiceImpl(TarefaPrevistaRepository tarefaPrevistaRepository, EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public List<TarefaPrevista> findAll() {
        List<TarefaPrevista> tp1 =new ArrayList<>();
        tarefaPrevistaRepository.findAll().forEach(tp1::add);
        return tp1;
    }

    @Override
    public Optional<TarefaPrevista> findById(Long id) {
        return tarefaPrevistaRepository.findById(id);
    }

    public TarefaEfetiva convertTarefaPevistaEfetiva(TarefaPrevista tp1){
        TarefaEfetiva t1 = new TarefaEfetiva();
        t1.setId(tp1.getId());
        t1.setNome(tp1.getNome());
        t1.setTarefaPrevista(tp1);
        return t1;
    }

    @Override
    public Optional<Empregado> atribuiTarefasEmpregados(String emailEmpregado, Long idTarefa) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findByEmail(emailEmpregado);
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(idTarefa);
        if(optionalEmpregado.isPresent()){
            if(optionalTarefaPrevista.isPresent()){
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

        }
        return Optional.empty();
    }
}
