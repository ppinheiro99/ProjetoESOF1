package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Cliente;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Optional<Cliente> createCliente(Cliente cliente);
    Optional<Cliente>deleteCliente(Long idCliente);
    Optional<Cliente> searchCliente(Map<String, String> query);
}
