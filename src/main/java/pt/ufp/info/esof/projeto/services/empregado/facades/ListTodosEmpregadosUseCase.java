package pt.ufp.info.esof.projeto.services.empregado.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListTodosEmpregadosUseCase {
    private final EmpregadoRepository empregadoRepository;

    public ListTodosEmpregadosUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public List<Empregado> findAll() {
            List<Empregado> e =new ArrayList<>();
            empregadoRepository.findAll().forEach(e::add);
            return e;
    }


}
