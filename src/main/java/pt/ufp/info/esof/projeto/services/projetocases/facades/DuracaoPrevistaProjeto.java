package pt.ufp.info.esof.projeto.services.projetocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;

@Service
public class DuracaoPrevistaProjeto {
    private final ProjetoRepository projetoRepository;

    public DuracaoPrevistaProjeto(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Float duracaoPrevistaProjeto(Long id) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(id);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            return projeto.duracaoPrevistaHoras();
        }
        return 0f;
    }
}
