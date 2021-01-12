package pt.ufp.info.esof.projeto.services;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmpregadoService{
    List<Empregado> findAll();
    Optional<Empregado> findById(Long id);
    Optional<Empregado> createEmpregado(Empregado empregado);
    Optional<Empregado> deleteEmpregado(String email);
    List<Empregado> searchEmpregado(Map<String, String> query);
}
