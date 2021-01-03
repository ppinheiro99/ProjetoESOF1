package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.*;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import java.util.Optional;

@Controller
@RequestMapping("/Projeto")
public class ProjetoController {
    private final ProjetoService projetoService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{id}/valor") // Possivelmente nao é bem assim ( so devo ter de imprimir o valor ), perguntar ao professor !!!
    public ResponseEntity<ProjetoResponseDTO> getCustoProjeto(@PathVariable Long id){
        Optional<Projeto> optionalProjeto = projetoService.custoPrevistoProjeto(id);
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/tempo") // o mesmo que o valor
    public ResponseEntity<ProjetoResponseDTO> getDuracaoProjeto(@PathVariable Long id){
        Optional<Projeto> optionalProjeto = projetoService.duracaoPrevistaProjeto(id);
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<CriarProjetoDTO> criarProjeto(@RequestBody CriarProjetoDTO projeto){
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(dtoStaticFactory.criarProjetoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PatchMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDTO> adicionaTarefa(@PathVariable Long projetoId, @RequestBody TarefaCreateDTO tarefa){
        Optional<Projeto> optionalProjeto = projetoService.adicionarTarefa(projetoId,tarefa.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
