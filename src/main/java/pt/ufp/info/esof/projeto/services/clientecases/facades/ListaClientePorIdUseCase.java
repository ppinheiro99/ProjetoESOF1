package pt.ufp.info.esof.projeto.services.clientecases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ListaClientePorIdUseCase {
    private final ClienteRepository clienteRepository;

    public ListaClientePorIdUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
}
