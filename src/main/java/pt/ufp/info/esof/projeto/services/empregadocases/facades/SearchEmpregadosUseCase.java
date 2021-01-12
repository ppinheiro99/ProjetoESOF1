package pt.ufp.info.esof.projeto.services.empregadocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.ArrayList;
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
        String stringCargo = query.get("query[cargo]");
        Cargo cargo;
        String stringIdTarefaEfetiva = query.get("query[idTarefaEfetiva]");
        Long idTarefaEfetiva;


        if (stringIdTarefaEfetiva != null && stringCargo != null) {
            idTarefaEfetiva = Long.parseLong(stringIdTarefaEfetiva);
            cargo = definirCargo(query.get("query[cargo]"));
            return empregadoRepository.pesquisaEmpregados(nome, email, cargo, idTarefaEfetiva);
        }

        if (stringIdTarefaEfetiva != null) {
            idTarefaEfetiva = Long.parseLong(stringIdTarefaEfetiva);
            return empregadoRepository.pesquisaEmpregados(nome, email, null, idTarefaEfetiva);

        }
        if (stringCargo != null) {

            cargo = definirCargo(query.get("query[cargo]"));
            return empregadoRepository.pesquisaEmpregados(nome, email, cargo, null);
        }

        return empregadoRepository.pesquisaEmpregados(nome, email, null, null);
    }

    private Cargo definirCargo(String cargo) {
        if (cargo.compareTo("analistaJunior") == 0) return Cargo.analistaJunior;
        if (cargo.compareTo("analistaSenior") == 0) return Cargo.analistaSenior;
        if (cargo.compareTo("desenvolvedorJunior") == 0) return Cargo.desenvolvedorJunior;
        if (cargo.compareTo("desenvolvedorSenior") == 0) return Cargo.desenvolvedorSenior;
        return null;
    }

}
