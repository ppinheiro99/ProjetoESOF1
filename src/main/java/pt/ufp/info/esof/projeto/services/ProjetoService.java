package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.Optional;

public interface ProjetoService {
    Optional<Projeto> criarProjeto(Projeto projeto);
}
