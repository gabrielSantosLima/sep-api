package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.services.AlunoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/alunos")
@SuppressWarnings("unused")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            val alunos = alunoService.listar();
            return ResponseEntity.ok(alunos);
        }catch(Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idAluno){
        try{
            val aluno = alunoService.encontrarPorId(idAluno);
            val alunoNaoExiste = !aluno.isPresent();
            if(alunoNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(aluno.get());
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
            val alunos = alunoService.encontrarPorNome(nome);
            return ResponseEntity.ok(alunos);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}
