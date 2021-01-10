package pt.ufp.info.esof.projeto.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjetoService projetoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProjetoResponseDTO>> getAllProjetos() {
        this.logger.info("Received a get request");
        List<ProjetoResponseDTO> responseDTOS = new ArrayList<>();
        projetoService.findAll().forEach(projeto -> responseDTOS.add(dtoStaticFactory.projetoResponseDTO(projeto)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> getProjetoById(@PathVariable Long id) {
        this.logger.info("Received a get request");
        Optional<Projeto> optionalProjeto = projetoService.findById(id);
        return optionalProjeto.map(projeto -> {
            ProjetoResponseDTO projetoResponseDTO = dtoStaticFactory.projetoResponseDTO(projeto);
            return ResponseEntity.ok(projetoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/valor")
    public ResponseEntity<Float> getCustoProjeto(@PathVariable Long id) {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(projetoService.custoPrevistoProjeto(id));
    }

    @GetMapping("/{id}/tempo")
    public ResponseEntity<Float> getDuracaoProjeto(@PathVariable Long id) {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(projetoService.duracaoPrevistaProjeto(id));
    }

    @PostMapping
    public ResponseEntity<CriarProjetoDTO> criarProjeto(@RequestBody CriarProjetoDTO projeto){
        this.logger.info("Received a post request");
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(dtoStaticFactory.criarProjetoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idProjeto}")
    public ResponseEntity<Optional<Projeto>> deleteProjeto(@PathVariable Long idProjeto){
        this.logger.info("Received a delete request");
       return ResponseEntity.ok(projetoService.deleteProjeto(idProjeto));
    }

    @PatchMapping(value = "/{projetoid}/{idTarefa}")
    public ResponseEntity<ProjetoResponseDTO> patchTarefasProjeto(@PathVariable("projetoid") Long projetoid, @PathVariable("idTarefa") Long idTarefa) {
        this.logger.info("Received a patch request");
        Optional<Projeto> optionalProjeto = projetoService.assocTarefasProjeto(projetoid,idTarefa);
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
