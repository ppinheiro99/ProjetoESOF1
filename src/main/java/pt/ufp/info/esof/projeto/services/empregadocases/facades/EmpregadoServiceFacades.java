package pt.ufp.info.esof.projeto.services.empregadocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.EmpregadoService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpregadoServiceFacades implements EmpregadoService {
    private final EliminarEmpregadoUseCase eliminarEmpregadoUseCase;
    private final CriarEmpregadoUseCase criarEmpregadoUseCase;
    private final ListaEmpregadoPorIdUseCase listaEmpregadoPorIdUseCase;
    private final ListTodosEmpregadosUseCase listTodosEmpregadosUseCase;
    private final SearchEmpregadosUseCase searchEmpregadosUseCase;

    public EmpregadoServiceFacades(EliminarEmpregadoUseCase eliminarEmpregadoUseCase, CriarEmpregadoUseCase criarEmpregadoUseCase, ListaEmpregadoPorIdUseCase listaEmpregadoPorIdUseCase, ListTodosEmpregadosUseCase listTodosEmpregadosUseCase, SearchEmpregadosUseCase searchEmpregadosUseCase) {
        this.eliminarEmpregadoUseCase = eliminarEmpregadoUseCase;
        this.criarEmpregadoUseCase = criarEmpregadoUseCase;
        this.listaEmpregadoPorIdUseCase = listaEmpregadoPorIdUseCase;
        this.listTodosEmpregadosUseCase = listTodosEmpregadosUseCase;
        this.searchEmpregadosUseCase = searchEmpregadosUseCase;
    }

    @Override
    public List<Empregado> findAll() {
        return listTodosEmpregadosUseCase.findAll();
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return listaEmpregadoPorIdUseCase.findById(id);
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        return criarEmpregadoUseCase.createEmpregado(empregado);
    }

    @Override
    public Optional<Empregado> deleteEmpregado(String email) {
        return eliminarEmpregadoUseCase.deleteEmpregado(email);
    }

    @Override
    public List<Empregado> searchEmpregado(Map<String, String> query) {
        return searchEmpregadosUseCase.pesquisarEmpregado(query);
    }

}
