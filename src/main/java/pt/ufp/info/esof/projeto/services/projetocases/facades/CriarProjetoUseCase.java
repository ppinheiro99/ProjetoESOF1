package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;
@Service
public class CriarProjetoUseCase {
    private final ProjetoRepository projetoRepository;

    public CriarProjetoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Projeto> criarProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projeto.getId());
        if(optionalProjeto.isEmpty()){
            return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
    }
}
