package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.*;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ClienteResponseDTO>> getAllClientes(){
        List<ClienteResponseDTO> responseDTOS=new ArrayList<>();
        clienteService.findAll().forEach(cliente -> responseDTOS.add(dtoStaticFactory.clienteResponseDTO(cliente)));
        return ResponseEntity.ok(responseDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id){
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        return optionalCliente.map(cliente -> {
            ClienteResponseDTO clienteResponseDTO = dtoStaticFactory.clienteResponseDTO(cliente);
            return ResponseEntity.ok(clienteResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> createCliente(@RequestBody CriarClienteDTO cliente){
        Optional<Cliente> optionalCliente = clienteService.createCliente(cliente.converter());
        return optionalCliente.map(value -> ResponseEntity.ok(dtoStaticFactory.clienteResponseDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Optional<Cliente>> deleteCliente(@PathVariable Long idCliente){
        return ResponseEntity.ok(clienteService.deleteCliente(idCliente));
    }
}
