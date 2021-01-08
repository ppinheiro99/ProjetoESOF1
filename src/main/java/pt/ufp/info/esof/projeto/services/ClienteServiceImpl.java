package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> c =new ArrayList<>();
        clienteRepository.findAll().forEach(c::add);
        return c;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> createCliente(Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(cliente.getEmail());
        if(!optionalCliente.isPresent()){
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

}
