package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.services.ProfessorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idProfessor){
        try{
            val professor = professorService.encontrarPorId(idProfessor);
            val professorNaoExiste = !professor.isPresent();
            if(professorNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(professor.get());
        }catch(Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<Object> encontrarPorNome(@RequestParam String nome){
        try{
            val professores = professorService.encontrarPorNome(nome);
            return ResponseEntity.ok(professores);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}
