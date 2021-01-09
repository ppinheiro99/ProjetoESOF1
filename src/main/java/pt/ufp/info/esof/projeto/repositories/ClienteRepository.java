package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String email);
    @Query("SELECT c FROM Cliente c where (:nome is null or c.nome=:nome) and (:email is null or c.email=:email)")
    List<Cliente> pesquisaClientes(@Param("nome") String nome, @Param("email") String email);
}
