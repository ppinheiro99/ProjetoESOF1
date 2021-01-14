package pt.ufp.info.esof.projeto.services.empregadocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.Optional;

@Service
public class CriarEmpregadoUseCase {
    private final EmpregadoRepository empregadoRepository;

    public CriarEmpregadoUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public Optional<Empregado> createEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if (optionalEmpregado.isEmpty()) {
            empregadoRepository.save(empregado);
            return Optional.of(empregado);
        }
        return Optional.empty();
    }

}
