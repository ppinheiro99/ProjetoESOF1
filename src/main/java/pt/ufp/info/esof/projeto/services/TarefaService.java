package pt.ufp.info.esof.projeto.services;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.Optional;

public interface TarefaService{
    List<TarefaPrevista> findAll();
    Optional<TarefaPrevista> findById(Long id);
    Optional<Empregado> atribuiTarefasEmpregados(String emailEmpregado, Long idTarefa);
    Optional<TarefaPrevista> createTarefa(TarefaPrevista converter);
    Optional<TarefaPrevista> deleteTarefa(Long idTarefa);
    Optional<TarefaEfetiva> atribuiHorasTarefa(Long idTarefa,Float duracaoHoras);
    Optional<TarefaEfetiva> concluirTarefa(Long idTarefa);
}
