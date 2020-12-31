package pt.ufp.info.esof.projeto.services;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.Optional;

public interface GestorProjetoService{
    Optional<Projeto> criarProjeto(Projeto projeto);
}
