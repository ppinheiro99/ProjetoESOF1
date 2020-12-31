package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

@Repository
public interface TarefaRepository extends CrudRepository<TarefaEfetiva,Long> {
}
