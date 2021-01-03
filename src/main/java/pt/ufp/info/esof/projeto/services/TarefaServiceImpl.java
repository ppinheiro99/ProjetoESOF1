package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TarefaServiceImpl implements TarefaService{
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    public TarefaServiceImpl(TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
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
}
