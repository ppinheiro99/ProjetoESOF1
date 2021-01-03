package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Empregado;

import java.util.Optional;
public interface GestorProjetoService{
    Optional<Empregado> atribuiTarefasEmpregados(Long idEmpregado, Long idTarefa);
}
