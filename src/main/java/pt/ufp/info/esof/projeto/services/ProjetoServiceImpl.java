package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;
@Service
public class ProjetoServiceImpl implements ProjetoService{
    private final ProjetoRepository projetoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository, ClienteRepository clienteRepository) {
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

    @Override
    public Optional<Projeto> adicionarTarefa(Long projetoId, TarefaPrevista tarefa) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            int quantidadeDeTarefasAntes=projeto.getTarefaPrevistas().size();
            projeto.adicionarTarefas(tarefa);
            int quantidadeDeTarefasDepois=projeto.getTarefaPrevistas().size();
            if(quantidadeDeTarefasDepois != quantidadeDeTarefasAntes) {
                return Optional.of(projeto);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> custoPrevistoProjeto(Long projetoId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            projeto.custoPrevistoProjeto();
            return Optional.of(projeto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> duracaoPrevistaProjeto(Long projetoId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            projeto.custoPrevistoProjeto();
            return Optional.of(projeto);
        }
        return Optional.empty();
    }
}
