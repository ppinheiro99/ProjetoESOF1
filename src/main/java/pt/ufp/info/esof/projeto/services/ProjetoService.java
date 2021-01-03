package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.Optional;

public interface ProjetoService {
    Optional<Projeto> criarProjeto(Projeto projeto);
    Optional<Projeto> adicionarTarefa(Long projetoId, TarefaPrevista tarefa);
    Optional<Projeto> custoPrevistoProjeto(Long id);
    Optional<Projeto> duracaoPrevistaProjeto(Long id);
}
