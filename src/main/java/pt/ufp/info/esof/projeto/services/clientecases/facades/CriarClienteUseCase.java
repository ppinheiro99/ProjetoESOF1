package pt.ufp.info.esof.projeto.services.clientecases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.Optional;
@Service
public class CriarClienteUseCase {
    private final ClienteRepository clienteRepository;

    public CriarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> createCliente(Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(cliente.getEmail());
        if(optionalCliente.isEmpty()){
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }
}
