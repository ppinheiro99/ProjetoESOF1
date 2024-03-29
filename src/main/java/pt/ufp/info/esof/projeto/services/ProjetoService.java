package pt.ufp.info.esof.projeto.services;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProjetoService {
    Optional<Projeto> criarProjeto(Projeto projeto);
    Float custoPrevistoProjeto(Long id);
    Float duracaoPrevistaProjeto(Long id);
    List<Projeto> findAll();
    Optional<Projeto> findById(Long id);
    Optional<Projeto> deleteProjeto(Long idProjeto);
    Optional<Projeto> assocTarefasProjeto(Long projetoid,Long idTarefa);
    List<Projeto>  searchProjeto(Map<String, String> query);
}
