package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpregadoRepository extends CrudRepository<Empregado, Long> {
    Optional<Empregado> findByEmail(String emailEmpregado);

    @Query("SELECT e FROM Empregado e LEFT JOIN e.tarefaEfetivas t where (:nome is null or e.nome=:nome) and (:email is null or e.email=:email)  and (:cargo is null or e.cargo=:cargo) and (:idTarefaEfetiva is null or t.id=:idTarefaEfetiva)")
    List<Empregado> pesquisaEmpregados(@Param("nome") String nome, @Param("email") String email, @Param("cargo") Cargo cargo, @Param("idTarefaEfetiva") Long idTarefaEfetiva);

}