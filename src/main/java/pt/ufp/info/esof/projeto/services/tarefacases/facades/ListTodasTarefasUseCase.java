package pt.ufp.info.esof.projeto.services.tarefacases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListTodasTarefasUseCase {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    public ListTodasTarefasUseCase(TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    public List<TarefaPrevista> findAll() {
        List<TarefaPrevista> tp1 =new ArrayList<>();
        tarefaPrevistaRepository.findAll().forEach(tp1::add);
        return tp1;
    }
}
