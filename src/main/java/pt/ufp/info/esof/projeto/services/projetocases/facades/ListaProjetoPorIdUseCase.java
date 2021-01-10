package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;
@Service
public class ListaProjetoPorIdUseCase {
    private final ProjetoRepository projetoRepository;
    public ListaProjetoPorIdUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }
}
