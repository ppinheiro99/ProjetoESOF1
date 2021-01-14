package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Estados;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.List;
import java.util.Map;

@Service
public class SearchProjetoUseCase {
    private final ProjetoRepository projetoRepository;

    public SearchProjetoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> pesquisarProjeto(Map<String, String> query) {
        String nomeCliente = query.get("query[nomeCliente]");
        String nome = query.get("query[nome]");
        if(query.get("query[estado]") != null){
            if(query.get("query[estado]").equals("Concluido") || query.get("query[estado]").equals("EmAndamento") || query.get("query[estado]").equals("Atrasado") || query.get("query[estado]").equals("NaoComecado") || query.get("query[estado]").equals ("ConcluidoComAtraso") ){
                Estados estado = Estados.valueOf(query.get("query[estado]"));
                return projetoRepository.pesquisaProjeto(nomeCliente,nome,estado);
            }
        }
        return projetoRepository.pesquisaProjeto(nomeCliente,nome,null);
    }
}
