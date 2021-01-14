package pt.ufp.info.esof.projeto.services.tarefacases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TarefaServiceFacades implements TarefaService {
    private final EliminarTarefaUseCase eliminarTarefaUseCase;
    private final CriarTarefaUseCase criarTarefaUseCase;
    private final ListaTarefaPorIdUseCase listaExplicadorPorIdUseCase;
    private final ListTodasTarefasUseCase listTodasTarefasUseCase;
    private final AtribuiTarefaEmpregado atribuiTarefaEmpregado;
    private final AtribuiHorasTarefa atribuiHorasTarefa;
    private final ConcluirTarefa concluirTarefa;
    private final SearchTarefasUseCase pesquisarTarefas;

    public TarefaServiceFacades(EliminarTarefaUseCase eliminarTarefaUseCase, CriarTarefaUseCase criarTarefaUseCase, ListaTarefaPorIdUseCase listaExplicadorPorIdUseCase, ListTodasTarefasUseCase listTodasTarefasUseCase, AtribuiTarefaEmpregado atribuiTarefaEmpregado, AtribuiHorasTarefa atribuiHorasTarefa, ConcluirTarefa concluirTarefa, SearchTarefasUseCase pesquisarTarefas) {
        this.eliminarTarefaUseCase = eliminarTarefaUseCase;
        this.criarTarefaUseCase = criarTarefaUseCase;
        this.listaExplicadorPorIdUseCase = listaExplicadorPorIdUseCase;
        this.listTodasTarefasUseCase = listTodasTarefasUseCase;
        this.atribuiTarefaEmpregado = atribuiTarefaEmpregado;
        this.atribuiHorasTarefa = atribuiHorasTarefa;
        this.concluirTarefa = concluirTarefa;
        this.pesquisarTarefas = pesquisarTarefas;
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
        return atribuiTarefaEmpregado.atribuiTarefasEmpregados(emailEmpregado,idTarefa);
    }

    @Override
    public Optional<TarefaPrevista> createTarefa(TarefaPrevista tarefaPrevista) {
        return criarTarefaUseCase.createTarefa(tarefaPrevista);
    }

    @Override
    public Optional<TarefaPrevista> deleteTarefa(Long idTarefa) {
        return eliminarTarefaUseCase.deleteTarefa(idTarefa);
    }

    @Override
    public Optional<TarefaEfetiva> atribuiHorasTarefa(Long idTarefa,Float duracaoHoras) {
        return atribuiHorasTarefa.atribuiHoras(idTarefa, duracaoHoras);
    }

    @Override
    public Optional<TarefaEfetiva> concluirTarefa(Long idTarefa) {
        return concluirTarefa.terminarTarefa(idTarefa);
    }

    @Override
    public List<TarefaPrevista> pesquisarTarefas(Map<String, String> query) {
        return pesquisarTarefas.pesquisarTarefas(query);
    }
}
