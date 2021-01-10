package pt.ufp.info.esof.projeto.services.projetocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceFacades implements ProjetoService {
    private final CriarProjetoUseCase criarProjetoUseCase;
    private final deleteProjetoUseCase deleteProjetoUseCase;
    private final ListaProjetoPorIdUseCase listaProjetoPorIdUseCase;
    private final ListTodosProjetosUseCase listTodosProjetosUseCase;
    private final CustoPrevistoProjetoUseCase custoPrevistoProjetoUseCase;
    private final DuracaoPrevistaProjetoUseCase duracaoPrevistaProjetoUseCase;

    public ProjetoServiceFacades(deleteProjetoUseCase deleteProjetoUseCase, CriarProjetoUseCase criarProjetoUseCase, ListaProjetoPorIdUseCase listaProjetoPorIdUseCase, ListTodosProjetosUseCase listTodosProjetosUseCase, CustoPrevistoProjetoUseCase custoPrevistoProjetoUseCase, DuracaoPrevistaProjetoUseCase duracaoPrevistaProjetoUseCase) {
        this.deleteProjetoUseCase = deleteProjetoUseCase;
        this.criarProjetoUseCase = criarProjetoUseCase;
        this.listaProjetoPorIdUseCase = listaProjetoPorIdUseCase;
        this.listTodosProjetosUseCase = listTodosProjetosUseCase;
        this.custoPrevistoProjetoUseCase = custoPrevistoProjetoUseCase;
        this.duracaoPrevistaProjetoUseCase = duracaoPrevistaProjetoUseCase;
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
            return custoPrevistoProjetoUseCase.custoPrevistoProjeto(id);
        }



    @Override
    public Float duracaoPrevistaProjeto(Long id) {
        return duracaoPrevistaProjetoUseCase.duracaoPrevistaProjeto(id);
    }


    @Override
    public Optional<Projeto> deleteProjeto(Long idProjeto) {
        return deleteProjetoUseCase.deleteProjeto(idProjeto);
    }

    @Override
    public Optional<Projeto> assocTarefasProjeto(Long projetoid, Long idTarefa) {
        return Optional.empty();
    }


}
