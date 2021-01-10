package pt.ufp.info.esof.projeto.services.projetocases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ListTodosProjetosUseCase {
    private final ProjetoRepository projetoRepository;
    public ListTodosProjetosUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> findAll() {
        List<Projeto> p1 =new ArrayList<>();
        projetoRepository.findAll().forEach(p1::add);
        return p1;
    }
}
