package pt.ufp.info.esof.projeto.services.tarefacases.facades;


import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;


import java.util.List;
import java.util.Map;
@Service
public class SearchTarefasUseCase {

    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    public SearchTarefasUseCase(TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    public List<TarefaPrevista> pesquisarTarefas(Map<String, String> query) {
        String nome = query.get("query[nome]");
        Long idProjeto = Long.parseLong(query.get("query[idProjeto]"));


        if(nome != null){
            return tarefaPrevistaRepository.pesquisarTarefas(nome,idProjeto);
        }
        return tarefaPrevistaRepository.pesquisarTarefas(nome,idProjeto);
    }


}
