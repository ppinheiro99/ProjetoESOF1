package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.projeto.dtos.DTOStaticFactory;
import pt.ufp.info.esof.projeto.dtos.CriarProjetoDTO;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.GestorProjetoService;

import java.util.Optional;
@Controller
@RequestMapping("/gestor")
public class GestorProjetoController {
    private final GestorProjetoService gestorProjetoService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public GestorProjetoController(GestorProjetoService gestorProjetoService) {
        this.gestorProjetoService = gestorProjetoService;
    }

    @PostMapping
    public ResponseEntity<CriarProjetoDTO> criarProjeto(@RequestBody CriarProjetoDTO projeto){
        Optional<Projeto> optionalProjeto = gestorProjetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(dtoStaticFactory.criarProjetoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
