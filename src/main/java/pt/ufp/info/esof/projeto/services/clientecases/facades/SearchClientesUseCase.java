package pt.ufp.info.esof.projeto.services.clientecases.facades;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.List;
import java.util.Map;

@Service
public class SearchClientesUseCase {
    private final ClienteRepository clienteRepository;

    public SearchClientesUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> pesquisarCliente(Map<String, String> query) {
        String nome = query.get("query[nome]");
        String email = query.get("query[email]");
        String nomeProjeto = query.get("query[nomeProjeto]");
        String idProjeto = query.get("query[idProjeto]");
        if(idProjeto != null){
            return clienteRepository.pesquisaClientes(nome,email,nomeProjeto,Long.parseLong(idProjeto));
        }
        return clienteRepository.pesquisaClientes(nome,email,nomeProjeto,null);
    }
}
