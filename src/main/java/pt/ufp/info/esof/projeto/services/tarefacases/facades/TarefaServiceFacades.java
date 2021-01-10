package pt.ufp.info.esof.projeto.services.tarefacases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.List;
import java.util.Optional;


@Service
public class TarefaServiceFacades implements TarefaService {
    private final EliminarTarefaUseCase eliminarTarefaUseCase;
    private final CriarTarefaUseCase criarTarefaUseCase;
    private final ListaTarefaPorIdUseCase listaExplicadorPorIdUseCase;
    private final ListTodasTarefasUseCase listTodasTarefasUseCase;
    private final AtribuiTarefaEmpregado atribuiTarefaEmpregado;

    public TarefaServiceFacades(EliminarTarefaUseCase eliminarTarefaUseCase, CriarTarefaUseCase criarTarefaUseCase, ListaTarefaPorIdUseCase listaExplicadorPorIdUseCase, ListTodasTarefasUseCase listTodasTarefasUseCase, AtribuiTarefaEmpregado atribuiTarefaEmpregado) {
        this.eliminarTarefaUseCase = eliminarTarefaUseCase;
        this.criarTarefaUseCase = criarTarefaUseCase;
        this.listaExplicadorPorIdUseCase = listaExplicadorPorIdUseCase;
        this.listTodasTarefasUseCase = listTodasTarefasUseCase;
        this.atribuiTarefaEmpregado = atribuiTarefaEmpregado;
    }

    @Override
    public List<TarefaPrevista> findAll() {
        return listTodasTarefasUseCase.findAll();
    }

    @Override
    public Optional<TarefaPrevista> findById(Long id) {
        return listaExplicadorPorIdUseCase.findById(id);
    }

    @Override
    public Optional<Empregado> atribuiTarefasEmpregados(String emailEmpregado, Long idTarefa) {
        Optional<Empregado> e = atribuiTarefaEmpregado.atribuiTarefasEmpregados(emailEmpregado,idTarefa);
        //return atribuiTarefaEmpregado.atribuiTarefasEmpregados(emailEmpregado,idTarefa);
        System.out.println(e);
        return null;
    }

    @Override
    public Optional<TarefaPrevista> createTarefa(TarefaPrevista tarefaPrevista) {
        return criarTarefaUseCase.createTarefa(tarefaPrevista);
    }

    @Override
    public Optional<TarefaPrevista> deleteTarefa(Long idTarefa) {
        return eliminarTarefaUseCase.deleteTarefa(idTarefa);
    }
}
