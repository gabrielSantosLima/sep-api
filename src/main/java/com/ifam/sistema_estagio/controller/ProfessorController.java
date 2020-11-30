package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.services.ProfessorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@SuppressWarnings("unused")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> listar(){
        try{
            val professores = professorService.listar();
            return ResponseEntity.ok(professores);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<Professor> encontrarPorId(@PathVariable String idProfessor){
        try{
            val professor = professorService.encontrarPorId(idProfessor);
            val professorNaoExiste = !professor.isPresent();
            if(professorNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(professor.get());
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<List<Professor>> encontrarPorNome(@RequestParam String nome){
        try{
            val professores = professorService.encontrarPorNome(nome);
            return ResponseEntity.ok(professores);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
