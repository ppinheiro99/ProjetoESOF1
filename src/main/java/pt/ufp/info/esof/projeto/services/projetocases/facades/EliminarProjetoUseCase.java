package pt.ufp.info.esof.projeto.services.projetocases.facades;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import pt.ufp.info.esof.projeto.services.tarefacases.facades.EliminarTarefaUseCase;

import java.util.Optional;

@Service
public class EliminarProjetoUseCase {
    private final ProjetoRepository projetoRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final EliminarTarefaUseCase eliminarTarefaUseCase;

    public EliminarProjetoUseCase(ProjetoRepository projetoRepository, TarefaPrevistaRepository tarefaPrevistaRepository, EliminarTarefaUseCase eliminarTarefaUseCase) {
        this.projetoRepository = projetoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.eliminarTarefaUseCase = eliminarTarefaUseCase;
    }

    public void removeTarefasProjeto(TarefaPrevista tp){
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(tp.getId());
        if(optionalTarefaPrevista.isPresent()){
            TarefaPrevista tp1 = optionalTarefaPrevista.get();
            //tarefaService.removeLigacoesComTarefa(tp1); // estava assim se nao der voltar a meter como estava
            eliminarTarefaUseCase.deleteTarefa(tp1.getId()); /////////////////////// VErificar se isto esta bem
            tarefaPrevistaRepository.delete(tp1);
        }
    }

    public Optional<Projeto> deleteProjeto(Long idProjeto) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(idProjeto);
        if(optionalProjeto.isPresent()){
            Projeto p = optionalProjeto.get();
            if(!p.getTarefaPrevistas().isEmpty()){
                for (TarefaPrevista tp:p.getTarefaPrevistas()) {
                    removeTarefasProjeto(tp); // removo todas as ligacoes entre as tarefas e das tarefas
                    if(p.getTarefaPrevistas().isEmpty()){ // como estou a remover enquanto percorro se nao fizer esta condicao ele parte
                        break;
                    }
                }
                p.getTarefaPrevistas().clear(); // removo o array de tarefas
            }
            p.getCliente().getProjetos().remove(p);
            p.setCliente(null);
            projetoRepository.delete(p);
            return Optional.of(p);
        }
        return Optional.empty();
    }

}
