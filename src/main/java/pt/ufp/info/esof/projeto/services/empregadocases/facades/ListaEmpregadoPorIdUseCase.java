package pt.ufp.info.esof.projeto.services.empregadocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.Optional;
@Service
public class ListaEmpregadoPorIdUseCase {
    private final EmpregadoRepository empregadoRepository;

    public ListaEmpregadoPorIdUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public Optional<Empregado> findById(Long id) {
            return empregadoRepository.findById(id);
    }
}
