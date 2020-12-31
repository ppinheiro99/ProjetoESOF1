package pt.ufp.info.esof.projeto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarProjetoDTO implements CreateDTO<Projeto> {
    private String nome;
    private Long clienteID;

    @Override
    public Projeto converter(){
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        Cliente cliente = new Cliente();
        cliente.setId(clienteID);
        projeto.setCliente(cliente);

        return projeto;
    }
}
