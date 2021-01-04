package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProjetoServiceImpl implements ProjetoService{
    private final ProjetoRepository projetoRepository;
    private final ClienteRepository clienteRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository, ClienteRepository clienteRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.projetoRepository = projetoRepository;
        this.clienteRepository = clienteRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }
    @Override
    public List<Projeto> findAll() {
        List<Projeto> p1 =new ArrayList<>();
        projetoRepository.findAll().forEach(p1::add);
        return p1;
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
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
            if(!optionalProjeto.get().getTarefaPrevistas().contains(tarefa)) {
                Projeto projeto = optionalProjeto.get();
                int quantidadeDeTarefasAntes = projeto.getTarefaPrevistas().size();
                projeto.adicionarTarefas(tarefa);
                int quantidadeDeTarefasDepois = projeto.getTarefaPrevistas().size();
                if (quantidadeDeTarefasDepois != quantidadeDeTarefasAntes) {
                    return Optional.of(projeto);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> associarTarefa(Long projetoId, Long tarefaId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        Optional<TarefaPrevista> optionalTarefa = this.tarefaPrevistaRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            if(!optionalProjeto.get().getTarefaPrevistas().contains(optionalTarefa.get())) {
                Projeto projeto = optionalProjeto.get();
                TarefaPrevista tp1 = optionalTarefa.get();
                int quantidadeDeTarefasAntes = projeto.getTarefaPrevistas().size();
                projeto.adicionarTarefas(tp1);
                int quantidadeDeTarefasDepois = projeto.getTarefaPrevistas().size();
                if (quantidadeDeTarefasDepois != quantidadeDeTarefasAntes) {
                    return Optional.of(projeto);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Float custoPrevistoProjeto(Long projetoId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            return projeto.custoPrevistoProjeto();
        }
        return 0f;
    }

    @Override
    public Float duracaoPrevistaProjeto(Long projetoId) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            return projeto.duracaoPrevistaHoras();
        }
        return 0f;
    }
}
