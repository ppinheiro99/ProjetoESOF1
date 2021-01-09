package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.*;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ClienteService clienteService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ClienteResponseDTO>> getAllClientes(){
        this.logger.info("Received a get request");
        List<ClienteResponseDTO> responseDTOS=new ArrayList<>();
        clienteService.findAll().forEach(cliente -> responseDTOS.add(dtoStaticFactory.clienteResponseDTO(cliente)));
        return ResponseEntity.ok(responseDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id){
        this.logger.info("Received a get request");
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        return optionalCliente.map(cliente -> {
            ClienteResponseDTO clienteResponseDTO = dtoStaticFactory.clienteResponseDTO(cliente);
            return ResponseEntity.ok(clienteResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> createCliente(@RequestBody CriarClienteDTO cliente){
        this.logger.info("Received a post request");
        Optional<Cliente> optionalCliente = clienteService.createCliente(cliente.converter());
        return optionalCliente.map(value -> ResponseEntity.ok(dtoStaticFactory.clienteResponseDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Optional<Cliente>> deleteCliente(@PathVariable Long idCliente){
        this.logger.info("Received a delete request");
        return ResponseEntity.ok(clienteService.deleteCliente(idCliente));
    }
}
