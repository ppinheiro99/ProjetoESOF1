package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void pesquisaClientes() {
     //   Cliente cliente = new Cliente();
      //  cliente.setNome("P");
      //  cliente.setEmail("1111");
//        Projeto p = new Projeto();
//        p.setId(6L);
//        cliente.setId(5L);
//        cliente.getProjetos().add(p);
//        p.setCliente(cliente);
       // clienteRepository.save(cliente);
        System.out.println(clienteRepository.count());
        //System.out.println(clienteRepository.pesquisaClientes("Pedro", "36763@ufp.edu.pt"));
       // List<Cliente> c = clienteRepository.pesquisaClientes(null, "1111");
       // assertEquals(1, c.size());
    }
}