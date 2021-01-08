package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.CriarEmpregadoDTO;
import pt.ufp.info.esof.projeto.dtos.DTOStaticFactory;
import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.EmpregadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<EmpregadoResponseDTO>> getAllEmpregados(){
        List<EmpregadoResponseDTO> responseDTOS=new ArrayList<>();
        empregadoService.findAll().forEach(empregado -> responseDTOS.add(dtoStaticFactory.empregadoResponseDTO(empregado)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpregadoResponseDTO> getEmpregadoById(@PathVariable Long id){
        Optional<Empregado> optionalEmpregado = empregadoService.findById(id);
        return optionalEmpregado.map(empregado -> {
            EmpregadoResponseDTO empregadoResponseDTO = dtoStaticFactory.empregadoResponseDTO(empregado);
            return ResponseEntity.ok(empregadoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<EmpregadoResponseDTO> createEmpregado(@RequestBody CriarEmpregadoDTO empregado){
        Optional<Empregado> optionalEmpregado = empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(dtoStaticFactory.criarEmpregadoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{emailEmpregado}")
    public ResponseEntity<Optional<Empregado>> deleteEmpregado(@PathVariable String emailEmpregado){
        return ResponseEntity.ok(empregadoService.deleteEmpregado(emailEmpregado));
    }
}
