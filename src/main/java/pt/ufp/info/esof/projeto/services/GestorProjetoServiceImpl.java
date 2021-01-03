package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;
@Service
public class GestorProjetoServiceImpl implements GestorProjetoService {
    private final ProjetoRepository projetoRepository;
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    public GestorProjetoServiceImpl(ProjetoRepository projetoRepository, EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.projetoRepository = projetoRepository;
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    public TarefaEfetiva convertTarefaPevistaEfetiva(TarefaPrevista tp1){
        TarefaEfetiva t1 = new TarefaEfetiva();
        t1.setId(tp1.getId());
        t1.setNome(tp1.getNome());
        t1.setTarefaPrevista(tp1);
        return t1;
    }

    // Tarefa efetiva é criada quando é associada uma tarefa ao funcionario
    // é enviada a tarefa prevista, criada a efetiva e de seguida faz-se as associacoes todas
    @Override
    public Optional<Empregado> atribuiTarefasEmpregados(Long idEmpregado, Long idTarefa) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findById(idEmpregado);
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(idTarefa);
        if(optionalEmpregado.isPresent()){
            if(optionalTarefaPrevista.isPresent()){
                TarefaPrevista tp1 = optionalTarefaPrevista.get();
                TarefaEfetiva t1 = convertTarefaPevistaEfetiva(tp1); // converve a tp1 para tarefa prevista
                t1.setEmpregado(optionalEmpregado.get()); // associa o empregado à tarefa e vice-versa
                tarefaEfetivaRepository.save(t1); // adiconar tarefa à  BD mal esta seja associada ao empregago
                optionalEmpregado.get().getTarefaEfetivas().add(t1); // so pode associar depois da tefetiva estiver na BD
                tp1.setTarefaEfetiva(t1);
                return optionalEmpregado;
            }

        }
        return Optional.empty();
    }
}
