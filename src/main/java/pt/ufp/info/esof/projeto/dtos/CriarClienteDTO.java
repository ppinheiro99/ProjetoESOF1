package pt.ufp.info.esof.projeto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Cliente;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarClienteDTO implements CreateDTO<Cliente> {
    private String nome;
    private String email;

    @Override
    public Cliente converter() {
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setNome(nome);

        return cliente;
    }
}
