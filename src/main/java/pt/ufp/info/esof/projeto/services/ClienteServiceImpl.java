package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;
    private final ProjetoServiceImpl projetoService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ProjetoServiceImpl projetoService) {
        this.clienteRepository = clienteRepository;
        this.projetoService = projetoService;
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

    @Override
    public Optional<Cliente> deleteCliente(Long idCliente) {
        Optional<Cliente> optionalCliente = clienteRepository
                .findById(idCliente);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            if(!cliente.getProjetos().isEmpty()) { // se tiver projeto temos de os eliminar
                System.out.println(cliente.getProjetos().size());
                for (Projeto p:cliente.getProjetos()) {
                    projetoService.deleteProjeto(p.getId()); // chamar o metodo já feito de remover projetos e todas as suas ligacoes
                    if(cliente.getProjetos().isEmpty()){
                        break;
                    }
                }
            }
            cliente.getProjetos().clear();
            clienteRepository.delete(cliente);
        }

        return Optional.empty();
    }

}
