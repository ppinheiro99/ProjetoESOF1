package pt.ufp.info.esof.projeto.dtos;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.stream.Collectors;

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

    public EmpregadoResponseDTO empregadoResponseDTO(Empregado empregado){
        List<String> tarefasEfetivas = empregado.getTarefaEfetivas().stream().map(TarefaEfetiva::getNome).collect(Collectors.toList());
        return EmpregadoResponseDTO.builder()
                .nome(empregado.getNome())
                .cargo(empregado.getCargo())
                .tarefasEfetivas(tarefasEfetivas)
                .build();
    }

    public CriarProjetoDTO criarProjetoDTO(Projeto projeto) {
        return CriarProjetoDTO
                .builder()
                .nome(projeto.getNome())
                .clienteID(projeto.getCliente().getId())
                .build();

    }
}
