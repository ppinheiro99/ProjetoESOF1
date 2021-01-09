package pt.ufp.info.esof.projeto.services;

import org.springframework.web.bind.annotation.PathVariable;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    Optional<Projeto> criarProjeto(Projeto projeto);
    Float custoPrevistoProjeto(Long id);
    Float duracaoPrevistaProjeto(Long id);
    List<Projeto> findAll();
    Optional<Projeto> findById(Long id);
    Optional<Projeto> deleteProjeto(Long idProjeto);
    Optional<Projeto> assocTarefasProjeto(Long projetoid,Long idTarefa);

}
