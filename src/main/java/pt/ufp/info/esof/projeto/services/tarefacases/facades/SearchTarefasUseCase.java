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
        String stringIdProjeto = query.get("query[idProjeto]");

        if (stringIdProjeto != null) {
            Long idProjeto = Long.parseLong(stringIdProjeto);
            return tarefaPrevistaRepository.pesquisarTarefas(nome, idProjeto);
        }
        return tarefaPrevistaRepository.pesquisarTarefas(nome, null);
    }


}
