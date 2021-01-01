package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.projeto.dtos.CriarProjetoDTO;
import pt.ufp.info.esof.projeto.dtos.DTOStaticFactory;
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

    @PostMapping
    public ResponseEntity<CriarProjetoDTO> criarProjeto(@RequestBody CriarProjetoDTO projeto){
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(dtoStaticFactory.criarProjetoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
