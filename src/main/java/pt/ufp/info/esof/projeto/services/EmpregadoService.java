package pt.ufp.info.esof.projeto.services;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.info.esof.projeto.models.Empregado;

import java.util.List;
import java.util.Optional;

public interface EmpregadoService extends CrudRepository<Empregado, Long> {

}
