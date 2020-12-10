package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.services.ProfessorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
@SuppressWarnings("unused")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            val professores = professorService.listar();
            return ResponseEntity.ok(professores);
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody UsuarioDto usuarioDto){
        try{
            val professor = professorService.salvar(usuarioDto.construirProfessor());
            return ResponseEntity.ok(professor);
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idProfessor){
        try{
            val professor = professorService.encontrarPorId(idProfessor);
            val professorNaoExiste = !professor.isPresent();
            if(professorNaoExiste) throw new Exception("Professor não encontrado");
            return ResponseEntity.ok(professor.get());
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<Object> encontrarPorNome(@RequestParam String nome){
        try{
            val professores = professorService.encontrarPorNome(nome);
            return ResponseEntity.ok(professores);
        }catch (Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/encontrar-matricula")
    public ResponseEntity<Object> encontrarPorMatricula(@RequestParam String matricula){
        try{
            val professores = professorService.encontrarPorMatricula(matricula);
            return ResponseEntity.ok(professores);
        }catch (Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }
}
