package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.services.AlunoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/estagio-pcct/{idEstagio}")
    public ResponseEntity<Object> listarPorEstagio(@PathVariable String idEstagio){
        try{
            val alunos = alunoService.encontrarPorEstagioPcct(idEstagio);
            return ResponseEntity.ok(alunos);
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idAluno){
        try{
            val aluno = alunoService.encontrarPorId(idAluno);
            val alunoNaoExiste = !aluno.isPresent();
            if(alunoNaoExiste) throw new Exception("Aluno não encontrado");
            return ResponseEntity.ok(aluno.get());
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<Object> encontrarPorNome(@RequestParam String nome){
        try{
            val alunos = alunoService.encontrarPorNome(nome);
            return ResponseEntity.ok(alunos);
        }catch (Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }
}
