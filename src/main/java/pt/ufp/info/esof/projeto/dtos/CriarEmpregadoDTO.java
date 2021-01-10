package pt.ufp.info.esof.projeto.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Empregado;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarEmpregadoDTO implements CreateDTO<Empregado>{
    private String nome;
    private Cargo cargo;
    private String email;

    @Override
    public Empregado converter(){
        Empregado empregado = new Empregado();
        empregado.setNome(nome);
        empregado.setEmail(email);
        empregado.setCargo(cargo);

        return empregado;
    }

}
