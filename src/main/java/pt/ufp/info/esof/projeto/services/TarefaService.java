package pt.ufp.info.esof.projeto.services;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.Optional;

public interface TarefaService{
    List<TarefaPrevista> findAll();
    Optional<TarefaPrevista> findById(Long id);
}
