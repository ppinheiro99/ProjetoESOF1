package pt.ufp.info.esof.projeto.services.clientecases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.ClienteService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteServiceFacades implements ClienteService {
    private final EliminarClienteUseCase eliminarClienteUseCase;
    private final CriarClienteUseCase criarClienteUseCase;
    private final ListaClientePorIdUseCase listaExplicadorPorIdUseCase;
    private final ListTodosClientesUseCase listTodosClientesUseCase;
    private final SearchClientesUseCase searchClientesUseCase;

    public ClienteServiceFacades(EliminarClienteUseCase eliminarClienteUseCase, CriarClienteUseCase criarClienteUseCase, ListaClientePorIdUseCase listaExplicadorPorIdUseCase, ListTodosClientesUseCase listTodosClientesUseCase, SearchClientesUseCase searchClientesUseCase) {
        this.eliminarClienteUseCase = eliminarClienteUseCase;
        this.criarClienteUseCase = criarClienteUseCase;
        this.listaExplicadorPorIdUseCase = listaExplicadorPorIdUseCase;
        this.listTodosClientesUseCase = listTodosClientesUseCase;
        this.searchClientesUseCase = searchClientesUseCase;
    }

    @Override
    public List<Cliente> findAll() {
        return listTodosClientesUseCase.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return listaExplicadorPorIdUseCase.findById(id);
    }

    @Override
    public Optional<Cliente> createCliente(Cliente cliente) {
       return criarClienteUseCase.createCliente(cliente);
    }

    @Override
    public Optional<Cliente> deleteCliente(Long idCliente) {
        return eliminarClienteUseCase.deleteCliente(idCliente);
    }

    @Override
    public List<Cliente> searchCliente(Map<String, String> query) {
        return searchClientesUseCase.pesquisarCliente(query);
    }
}
