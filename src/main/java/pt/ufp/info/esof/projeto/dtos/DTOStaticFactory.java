package pt.ufp.info.esof.projeto.dtos;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

/**
 * Fábrica estática para criação de DTO's
 * É implementada em conjunto com o padrão Singleton
 */
public class DTOStaticFactory {

    /**
     *
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

    public TarefasDTO tarefaDTO(TarefaEfetiva tarefaEfetiva){
        return TarefasDTO.builder()
                .nome(tarefaEfetiva.getNome())
                .empregadoNome(tarefaEfetiva.getEmpregado().getNome())
               // .projeto(tarefaEfetiva.getProjeto().getNome())
            //    .tempoPrevisto(tarefaEfetiva.getTempoPrevisto().getTempoPrevistoHoras())
             //   .concluida(tarefaEfetiva.isConcluida())
                .build();
    }

    public EmpregadoResponseDTO empregadoResponseDTO(Empregado empregado){
//        List<TarefasDTO> empregadoResponseDTOS= empregado.getTarefas().stream().map(tarefas ->
//                DTOStaticFactory.getInstance().tarefaDTO(tarefas)
//        ).collect(Collectors.toList());
//
//        System.out.println(empregadoResponseDTOS);

        return EmpregadoResponseDTO.builder()
                .nome(empregado.getNome())
                .cargo(empregado.getCargo())
                //.tarefas(empregadoResponseDTOS)
                .build();
    }

    public CriarProjetoDTO criarProjetoDTO(Projeto projeto){
        return CriarProjetoDTO
                .builder()
                .clienteID(projeto.getCliente().getId())
                .nome(projeto.getNome())
                .build();
    }
}