package pt.ufp.info.esof.projeto.dtos;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Tarefa;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Fábrica estática para criação de DTO's
 * É implementada em conjunto com o padrão Singleton
 */
public class DTOStaticFactory {

    /**
     *
     * Implementa a lógica necessária para garantir uma única instância da fábrica estática
     */
    private static DTOStaticFactory dtoAbstractFactory;

    private DTOStaticFactory() {
    }

    public static DTOStaticFactory getInstance(){
        if(dtoAbstractFactory==null){
            dtoAbstractFactory=new DTOStaticFactory();
        }
        return dtoAbstractFactory;
    }

    public TarefasDTO tarefaDTO(Tarefa tarefa){
        return TarefasDTO.builder()
                .nome(tarefa.getNome())
                .empregadoNome(tarefa.getEmpregado().getNome())
                .tempoEfetivo(tarefa.getTempoEfetivo().getTempoEfetivoHoras())
                .projeto(tarefa.getProjeto().getNome())
                .tempoPrevisto(tarefa.getTempoPrevisto().getTempoPrevistoHoras())
                .concluida(tarefa.isConcluida())
                .build();
    }

    public EmpregadoResponseDTO empregadoResponseDTO(Empregado empregado){
        List<TarefasDTO> empregadoResponseDTOS= empregado.getTarefas().stream().map(tarefas ->
                DTOStaticFactory.getInstance().tarefaDTO(tarefas)
        ).collect(Collectors.toList());

        return EmpregadoResponseDTO.builder()
                .nome(empregado.getNome())
                .cargo(empregado.getCargo())
                .tarefas(empregadoResponseDTOS)
                .build();
    }
}
