package pt.ufp.info.esof.projeto.services.empregadocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import java.util.List;
import java.util.Map;

import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

@Service
public class SearchEmpregadosUseCase {
    private final EmpregadoRepository empregadoRepository;


    public SearchEmpregadosUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public List<Empregado> pesquisarEmpregado(Map<String, String> query) {
        String nome = query.get("query[nome]");
        String email = query.get("query[email]");
        String stringIdTarefaEfetiva = query.get("query[idTarefaEfetiva]");
        Long idTarefaEfetiva;

        if (stringIdTarefaEfetiva != null) {
            idTarefaEfetiva = Long.parseLong(stringIdTarefaEfetiva);
            return empregadoRepository.pesquisaEmpregados(nome, email, idTarefaEfetiva);

        }
        return empregadoRepository.pesquisaEmpregados(nome, email,null);
    }

}
