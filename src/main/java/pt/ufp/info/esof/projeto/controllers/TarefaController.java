package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.*;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<TarefaResponseDTO>> getAllTarefas(){
        List<TarefaResponseDTO> responseDTOS=new ArrayList<>();
        tarefaService.findAll().forEach(tarefa -> responseDTOS.add(dtoStaticFactory.tarefaResponseDTO(tarefa)));
        return ResponseEntity.ok(responseDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> getTarefaById(@PathVariable Long id){
        Optional<TarefaPrevista> optionalTarefa = tarefaService.findById(id);
        return optionalTarefa.map(tarefa -> {
            TarefaResponseDTO tarefaResponseDTO = dtoStaticFactory.tarefaResponseDTO(tarefa);
            return ResponseEntity.ok(tarefaResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PatchMapping(value = "/{emailEmpregado}/{idTarefa}")
    public ResponseEntity<EmpregadoResponseDTO> patchTarefasEmpregado(@PathVariable("emailEmpregado") String emailEmpregado, @PathVariable("idTarefa") Long idTarefa) {
        Optional<Empregado> optionalEmpregado = tarefaService.atribuiTarefasEmpregados(emailEmpregado,idTarefa);
        return optionalEmpregado.map(empregado -> ResponseEntity.ok(dtoStaticFactory.empregadoResponseDTO(empregado))).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PostMapping()
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@RequestBody CriarTarefaPrevistaDTO tarefa){
        Optional<TarefaPrevista> optionalTarefa = tarefaService.createTarefa(tarefa.converter());
        return optionalTarefa.map(tarefap -> ResponseEntity.ok(dtoStaticFactory.tarefaResponseDTO(tarefap))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
