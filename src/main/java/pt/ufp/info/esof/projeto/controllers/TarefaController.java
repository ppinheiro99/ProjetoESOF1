package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.projeto.dtos.DTOStaticFactory;
import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaResponseDTO;
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
}
