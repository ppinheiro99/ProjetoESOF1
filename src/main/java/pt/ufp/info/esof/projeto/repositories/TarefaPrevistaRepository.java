package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaPrevistaRepository extends CrudRepository<TarefaPrevista,Long> {
    Optional<TarefaPrevista> findById(Long id);
    @Query("SELECT t FROM TarefaPrevista t join t.projeto p where (:nome is null or t.nome=:nome) and (:idProjeto is null or p.id=:idProjeto)")
    List<TarefaPrevista> pesquisarTarefas(@Param("nome") String nome,@Param("idProjeto") Long idProjeto);
}
