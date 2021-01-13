package pt.ufp.info.esof.projeto.services.tarefacases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class CriarTarefaUseCase {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final ProjetoRepository projetoRepository;

    public CriarTarefaUseCase(TarefaPrevistaRepository tarefaPrevistaRepository, ProjetoRepository projetoRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.projetoRepository = projetoRepository;
    }

    public Optional<TarefaPrevista> createTarefa(TarefaPrevista tarefaPrevista) {
        System.out.println(tarefaPrevista.getNome());
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(tarefaPrevista.getId());
        Optional<Projeto> optionalProjeto = projetoRepository.findById(tarefaPrevista.getProjeto().getId());
        if(optionalProjeto.isPresent()){
            if(optionalTarefaPrevista.isEmpty()){
                tarefaPrevistaRepository.save(tarefaPrevista);
                System.out.println(tarefaPrevista.getId());
                return Optional.of(tarefaPrevista);
            }
        }
        return Optional.empty();
    }
}
