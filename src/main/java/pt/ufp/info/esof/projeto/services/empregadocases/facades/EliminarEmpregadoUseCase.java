package pt.ufp.info.esof.projeto.services.empregadocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;

import java.util.Optional;
@Service
public class EliminarEmpregadoUseCase {
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    public EliminarEmpregadoUseCase(EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    public Optional<Empregado> deleteEmpregado(String email) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(email);
        if (optionalEmpregado.isPresent()) {
            Empregado e1 = optionalEmpregado.get();
            // se o empregado tiver tarefas temos de remover todas as ligacoes em "cascata"
            if (!e1.getTarefaEfetivas().isEmpty()) {
                for (TarefaEfetiva te : e1.getTarefaEfetivas()) {
                    te.setEmpregado(null); // remove o empregado da tarefa
                    te.getTarefaPrevista().setTarefaEfetiva(null); // remove a ligacao da tarefaPrevista com a Efetiva
                    te.setTarefaPrevista(null); // remove a ligacao da TarefaEfetiva com a tarefa prevista
                    tarefaEfetivaRepository.delete(te); // remove tarefa efetiva da base de dados
                }
                e1.getTarefaEfetivas().clear(); // "zera" o array list de tarefas efetivas do empregado
            }
            empregadoRepository.delete(optionalEmpregado.get()); // remove o empregado
        }
        return Optional.empty();
    }
}
