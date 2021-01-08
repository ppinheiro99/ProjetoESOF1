package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.empregadoRepository = empregadoRepository;
    }

    @Override
    public List<Empregado> findAll() {
        List<Empregado> e =new ArrayList<>();
        empregadoRepository.findAll().forEach(e::add);
        return e;
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return empregadoRepository.findById(id);
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if(!optionalEmpregado.isPresent()){
            return Optional.of( empregadoRepository.save(empregado));
        }
        return Optional.empty();
    }

    // remove as tarefas e o empregado
    public void removerTarefasEmpregado(Empregado e){
        for (TarefaEfetiva te:e.getTarefaEfetivas()) {
            te.setEmpregado(null); // remove o empregado da tarefa
            te.getTarefaPrevista().setTarefaEfetiva(null); // remove a ligacao da tarefaPrevista com a Efetiva
            te.setTarefaPrevista(null); // remove a ligacao da TarefaEfetiva com a tarefa prevista
            tarefaEfetivaRepository.delete(te); // remove tarefa efetiva da base de dados
        }
        e.getTarefaEfetivas().clear(); // "zera" o array list de tarefas efetivas do empregado
    }

    @Override
    public Optional<Empregado> deleteEmpregado(String email) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(email);
        if(optionalEmpregado.isPresent()){
            Empregado e1 = optionalEmpregado.get();
            // se o empregado tiver tarefas temos de remover todas as ligacoes em "cascata"
            if (!e1.getTarefaEfetivas().isEmpty()) {
                removerTarefasEmpregado(e1);
            }
            empregadoRepository.delete(optionalEmpregado.get()); // remove o empregado

        }
        return Optional.empty();
    }
}
