package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class TarefaServiceImpl implements TarefaService{
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final ProjetoRepository projetoRepository;
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    public TarefaServiceImpl(TarefaPrevistaRepository tarefaPrevistaRepository, ProjetoRepository projetoRepository, EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.projetoRepository = projetoRepository;
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public List<TarefaPrevista> findAll() {
        List<TarefaPrevista> tp1 =new ArrayList<>();
        tarefaPrevistaRepository.findAll().forEach(tp1::add);
        return tp1;
    }

    @Override
    public Optional<TarefaPrevista> findById(Long id) {
        return tarefaPrevistaRepository.findById(id);
    }

    public TarefaEfetiva convertTarefaPevistaEfetiva(TarefaPrevista tp1){
        TarefaEfetiva t1 = new TarefaEfetiva();
        t1.setId(tp1.getId());
        t1.setNome(tp1.getNome());
        t1.setTarefaPrevista(tp1);
        return t1;
    }

    @Override
    public Optional<Empregado> atribuiTarefasEmpregados(String emailEmpregado, Long idTarefa) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findByEmail(emailEmpregado);
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(idTarefa);
        if(optionalEmpregado.isPresent()){
            if(optionalTarefaPrevista.isPresent()){
                TarefaPrevista tp1 = optionalTarefaPrevista.get();
                if(!optionalEmpregado.get().getTarefaEfetivas().contains(tp1)) {
                    TarefaEfetiva t1 = convertTarefaPevistaEfetiva(tp1); // converve a tp1 para tarefa prevista
                    t1.setEmpregado(optionalEmpregado.get()); // associa o empregado à tarefa
                    tarefaEfetivaRepository.save(t1); // adiconar tarefa à  BD mal esta seja associada ao empregago
                    optionalEmpregado.get().getTarefaEfetivas().add(t1); // so pode associar depois da tefetiva estiver na BD
                    tp1.setTarefaEfetiva(t1);
                    return optionalEmpregado;
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<TarefaPrevista> createTarefa(TarefaPrevista tarefaPrevista) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(tarefaPrevista.getId());
        Optional<Projeto> optionalProjeto = projetoRepository.findById(tarefaPrevista.getProjeto().getId());
        if(optionalProjeto.isPresent()){
            if(!optionalTarefaPrevista.isPresent()){
                tarefaPrevistaRepository.save(tarefaPrevista);
                System.out.println(tarefaPrevista.getId());
                return Optional.of(tarefaPrevista);
            }
        }
        return Optional.empty();
    }

    public void removeLigacoesComTarefa(TarefaPrevista tp1){
        TarefaEfetiva te1 = tp1.getTarefaEfetiva();
        tp1.getProjeto().getTarefaPrevistas().remove(tp1); // removo a conexao com o projeto e vice-versa
        tp1.setProjeto(null);
        if(tp1.getTarefaEfetiva() != null){ // se tiver tarefas efetivas, é porque já tem um empregado associado à tarefa, temos de cortar essas conexoes
            te1.setTarefaPrevista(null); // removo a ligacao da tarefaEfetiva com a Tarefa prevista e vice-versa
            tp1.setTarefaEfetiva(null);
            te1.getEmpregado().getTarefaEfetivas().remove(te1); // removo a tarefa Efetiva do empregado e vice versa
            System.out.println(te1.getEmpregado().getNome());
            te1.setEmpregado(null);
        }
        tarefaEfetivaRepository.delete(te1); // como a efetiva depende da prevista ( e feita atraves dela ) quando a prevista é eliminada a efetiva tb tem de ser
    }

    @Override
    public Optional<TarefaPrevista> deleteTarefa(Long idTarefa) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(idTarefa);
        if(optionalTarefaPrevista.isPresent()){
            TarefaPrevista tp1 = optionalTarefaPrevista.get();
            removeLigacoesComTarefa(tp1);
            tarefaPrevistaRepository.delete(tp1);
        }
        return Optional.empty();
    }
}
