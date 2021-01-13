package pt.ufp.info.esof.projeto.services.clientecases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.services.projetocases.facades.EliminarProjetoUseCase;

import java.util.Optional;

@Service
public class EliminarClienteUseCase {
    private final ClienteRepository clienteRepository;
    private final EliminarProjetoUseCase eliminarProjetoUseCase;

    public EliminarClienteUseCase(ClienteRepository clienteRepository, EliminarProjetoUseCase eliminarProjetoUseCase) {
        this.clienteRepository = clienteRepository;
        this.eliminarProjetoUseCase = eliminarProjetoUseCase;
    }

    public Optional<Cliente> deleteCliente(Long idCliente) {
        Optional<Cliente> optionalCliente = clienteRepository
                .findById(idCliente);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();

            if(!cliente.getProjetos().isEmpty()) { // se tiver projeto temos de os eliminar
                System.out.println(cliente.getProjetos().size());
                for (Projeto p:cliente.getProjetos()) {
                    eliminarProjetoUseCase.deleteProjeto(p.getId()); // chamar o metodo já feito de remover projetos e todas as suas ligacoes
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
