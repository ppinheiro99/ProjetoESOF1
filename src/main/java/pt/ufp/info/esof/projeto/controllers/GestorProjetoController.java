package pt.ufp.info.esof.projeto.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.DTOStaticFactory;
import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.models.Empregado;
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

    @PutMapping(value = "/{idEmpregado}/{idTarefa}")
    public ResponseEntity<EmpregadoResponseDTO> putTarefasEmpregado(@PathVariable("idEmpregado") Long idEmpregado, @PathVariable("idTarefa") Long idTarefa) {
        Optional<Empregado> optionalEmpregado = gestorProjetoService.atribuiTarefasEmpregados(idEmpregado,idTarefa);
        return optionalEmpregado.map(empregado -> ResponseEntity.ok(dtoStaticFactory.empregadoResponseDTO(empregado))).orElseGet(() -> ResponseEntity.badRequest().build());

    }

}
