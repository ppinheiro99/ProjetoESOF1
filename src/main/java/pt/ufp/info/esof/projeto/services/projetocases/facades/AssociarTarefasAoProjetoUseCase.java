package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.ListaTarefaPorIdUseCase;

import java.util.Optional;


@Service
public class AssociarTarefasAoProjetoUseCase {
    private final ProjetoRepository projetoRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final ListaProjetoPorIdUseCase listaProjetoPorIdUseCase;
    private final ListaTarefaPorIdUseCase listaTarefaPorIdUseCase;


    public AssociarTarefasAoProjetoUseCase(ProjetoRepository projetoRepository, TarefaPrevistaRepository tarefaPrevistaRepository, ListaProjetoPorIdUseCase listaProjetoPorIdUseCase, ListaTarefaPorIdUseCase listaTarefaPorIdUseCase) {
        this.projetoRepository = projetoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.listaProjetoPorIdUseCase = listaProjetoPorIdUseCase;
        this.listaTarefaPorIdUseCase = listaTarefaPorIdUseCase;
    }


    public Optional<Projeto> assocTarefasProjeto(Long projetoid, Long idTarefa) {
        Optional<Projeto> optionalProjeto = listaProjetoPorIdUseCase.findById(projetoid);
        Optional<TarefaPrevista> optionalTarefaPrevista = listaTarefaPorIdUseCase.findById(idTarefa);

        if(optionalProjeto.isPresent() && optionalTarefaPrevista.isPresent())
            if(!optionalProjeto.get().getTarefaPrevistas().contains(optionalTarefaPrevista.get()))  //verifica se a tarefa já não estava associada ao projeto
            {
                optionalProjeto.get().adicionarTarefas(optionalTarefaPrevista.get());
                optionalTarefaPrevista.get().setProjeto(optionalProjeto.get());
                this.projetoRepository.save(optionalProjeto.get());
                this.tarefaPrevistaRepository.save(optionalTarefaPrevista.get());
                return optionalProjeto;
            }

        return Optional.empty();
    }
}

