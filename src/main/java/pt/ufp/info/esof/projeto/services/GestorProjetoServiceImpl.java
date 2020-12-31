package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;
@Service
public class GestorProjetoServiceImpl implements GestorProjetoService {
    private final ProjetoRepository projetoRepository;
    private final ClienteRepository clienteRepository;

    public GestorProjetoServiceImpl(ProjetoRepository projetoRepository, ClienteRepository clienteRepository) {
        this.projetoRepository = projetoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        Optional<Cliente> optionalCliente = clienteRepository
                .findById(projeto.getCliente().getId());
        if(optionalCliente.isPresent()){
                Cliente cliente = optionalCliente.get();
                cliente.getProjetos().add(projeto);
                projeto.setCliente(cliente);
                return Optional.of(projeto);
        }
        return Optional.empty();
    }
}
