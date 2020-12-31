package pt.ufp.info.esof.projeto.services;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.info.esof.projeto.models.Empregado;

public interface EmpregadoService extends CrudRepository<Empregado, Long> {

}
