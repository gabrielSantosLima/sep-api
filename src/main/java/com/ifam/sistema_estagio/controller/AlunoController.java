package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.services.AlunoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@SuppressWarnings("unused")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> listar(){
        try{
            val alunos = alunoService.listar();
            return ResponseEntity.ok(alunos);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<Aluno> encontrarPorId(@PathVariable String idAluno){
        try{
            val aluno = alunoService.encontrarPorId(idAluno);
            val alunoNaoExiste = !aluno.isPresent();
            if(alunoNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(aluno.get());
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<List<Aluno>> encontrarPorNome(@RequestParam String nome){
        try{
            val alunos = alunoService.encontrarPorNome(nome);
            return ResponseEntity.ok(alunos);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
