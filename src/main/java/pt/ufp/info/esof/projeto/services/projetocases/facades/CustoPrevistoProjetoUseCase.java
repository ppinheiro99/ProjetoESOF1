package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;
@Service
public class CustoPrevistoProjetoUseCase {
    private final ProjetoRepository projetoRepository;

    public CustoPrevistoProjetoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Float custoPrevistoProjeto(Long projetoId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            return projeto.custoPrevistoProjeto();
        }
        return 0f;
    }


}
