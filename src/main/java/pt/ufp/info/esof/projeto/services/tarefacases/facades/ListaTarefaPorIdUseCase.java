package pt.ufp.info.esof.projeto.services.tarefacases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class ListaTarefaPorIdUseCase {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    public ListaTarefaPorIdUseCase(TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    public Optional<TarefaPrevista> findById(Long id) {
        return tarefaPrevistaRepository.findById(id);
    }
}
