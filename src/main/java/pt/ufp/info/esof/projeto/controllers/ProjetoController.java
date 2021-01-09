package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.*;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final ProjetoService projetoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProjetoResponseDTO>> getAllProjetos() {
        List<ProjetoResponseDTO> responseDTOS = new ArrayList<>();
        projetoService.findAll().forEach(projeto -> responseDTOS.add(dtoStaticFactory.projetoResponseDTO(projeto)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> optionalProjeto = projetoService.findById(id);
        return optionalProjeto.map(projeto -> {
            ProjetoResponseDTO projetoResponseDTO = dtoStaticFactory.projetoResponseDTO(projeto);
            return ResponseEntity.ok(projetoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/valor")
    public ResponseEntity<Float> getCustoProjeto(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.custoPrevistoProjeto(id));
    }

    @GetMapping("/{id}/tempo")
    public ResponseEntity<Float> getDuracaoProjeto(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.duracaoPrevistaProjeto(id));
    }

    @PostMapping
    public ResponseEntity<CriarProjetoDTO> criarProjeto(@RequestBody CriarProjetoDTO projeto){
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(dtoStaticFactory.criarProjetoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idProjeto}")
    public ResponseEntity<Optional<Projeto>> deleteProjeto(@PathVariable Long idProjeto){
       return ResponseEntity.ok(projetoService.deleteProjeto(idProjeto));
    }
}
