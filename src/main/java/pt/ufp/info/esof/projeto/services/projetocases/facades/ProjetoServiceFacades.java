package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceFacades implements ProjetoService {
    private final CriarProjetoUseCase criarProjetoUseCase;
    private final EliminarProjetoUseCase eliminarProjetoUseCase;
    private final ListaProjetoPorIdUseCase listaProjetoPorIdUseCase;
    private final ListTodosProjetosUseCase listTodosProjetosUseCase;
    private final CustoPrevistoProjeto custoPrevistoProjeto;
    private final DuracaoPrevistaProjeto duracaoPrevistaProjeto;
    private final AssociarTarefasAoProjetoUseCase associarTarefasAoProjetoUseCase;

    public ProjetoServiceFacades(EliminarProjetoUseCase eliminarProjetoUseCase, CriarProjetoUseCase criarProjetoUseCase, ListaProjetoPorIdUseCase listaProjetoPorIdUseCase, ListTodosProjetosUseCase listTodosProjetosUseCase, CustoPrevistoProjeto custoPrevistoProjeto, DuracaoPrevistaProjeto duracaoPrevistaProjeto, AssociarTarefasAoProjetoUseCase associarTarefasAoProjetoUseCase) {
        this.eliminarProjetoUseCase = eliminarProjetoUseCase;
        this.criarProjetoUseCase = criarProjetoUseCase;
        this.listaProjetoPorIdUseCase = listaProjetoPorIdUseCase;
        this.listTodosProjetosUseCase = listTodosProjetosUseCase;
        this.custoPrevistoProjeto = custoPrevistoProjeto;
        this.duracaoPrevistaProjeto = duracaoPrevistaProjeto;
        this.associarTarefasAoProjetoUseCase = associarTarefasAoProjetoUseCase;
    }

    @Override
    public List<Projeto> findAll() {
        return listTodosProjetosUseCase.findAll();
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return listaProjetoPorIdUseCase.findById(id);
    }

    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        return criarProjetoUseCase.criarProjeto(projeto);
    }

    @Override
    public Float custoPrevistoProjeto(Long id) {
        return custoPrevistoProjeto.custoPrevistoProjeto(id);
    }

    @Override
    public Float duracaoPrevistaProjeto(Long id) {
        return duracaoPrevistaProjeto.duracaoPrevistaProjeto(id);
    }

    @Override
    public Optional<Projeto> deleteProjeto(Long idProjeto) {
        return eliminarProjetoUseCase.deleteProjeto(idProjeto);
    }

    @Override
    public Optional<Projeto> assocTarefasProjeto(Long projetoid, Long idTarefa) {
        return associarTarefasAoProjetoUseCase.assocTarefasProjeto(projetoid, idTarefa);
    }
}
