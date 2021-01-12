package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.Estados;
import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.List;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long> {
    @Query("SELECT p FROM Projeto p join p.cliente c where (:nomeCliente is null or c.nome=:nomeCliente) and (:nome is null or p.nome=:nome) and (:estado is null or p.estadoProjeto=:estado)")
    List<Projeto> pesquisaProjeto(@Param("nomeCliente") String nomeCliente, @Param("nome") String nome, @Param("estado") Estados estadoProjeto);
}
