package pt.ufp.info.esof.projeto.services.clientecases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ListTodosClientesUseCase {
    private final ClienteRepository clienteRepository;

    public ListTodosClientesUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        List<Cliente> clientes=new ArrayList<>();
        clienteRepository.findAll().forEach(clientes::add);
        return clientes;
    }
}
