package pt.ufp.info.esof.projeto.dtos;
import pt.ufp.info.esof.projeto.models.*;

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
                .email(empregado.getEmail())
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

    public ProjetoResponseDTO projetoResponseDTO(Projeto projeto) {
        List<String> tarefasPrevistas = projeto.getTarefaPrevistas().stream().map(TarefaPrevista::getNome).collect(Collectors.toList());

        return ProjetoResponseDTO.builder()
                .nome(projeto.getNome())
                .emailCliente(projeto.getCliente().getEmail())
                .tarefas(tarefasPrevistas)
                .custo(projeto.custoPrevistoProjeto())
                .custoEfetivo(projeto.custoEfetivoProjeto())
                .tempoHoras(projeto.duracaoPrevistaHoras())
                .estadoProjeto(projeto.estadoDoProjeto())
                .build();
    }
    public EmpregadoResponseDTO criarEmpregadoDTO(Empregado empregado) {
        return EmpregadoResponseDTO.builder()
                .nome(empregado.getNome())
                .email(empregado.getEmail())
                .cargo(empregado.getCargo())
                .build();
    }
    public TarefaResponseDTO tarefaResponseDTO(TarefaPrevista tarefa) {
        return TarefaResponseDTO.builder()
                .nome(tarefa.getNome())
                .tempoPrevistoHoras(tarefa.getTempoPrevistoHoras())
                .idProjeto(tarefa.getProjeto().getId())
                .build();
    }

    public ClienteResponseDTO clienteResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();
    }
}
