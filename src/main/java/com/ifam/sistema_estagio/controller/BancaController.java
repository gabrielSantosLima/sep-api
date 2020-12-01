package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.util.enums.Curso;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/bancas")
@SuppressWarnings("unused")
public class BancaController {

    @Autowired
    private BancaService bancaService;

    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(required = false) Curso curso){
        try{
            val bancas = bancaService.listarPorCurso(curso);
            return ResponseEntity.ok(bancas);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/{idBanca}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idBanca){
        try{
            val banca = bancaService.encontrarPorId(idBanca);
            val bancaNaoExiste = !banca.isPresent();
            if(bancaNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(banca.get());
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @DeleteMapping("/{idBanca}")
    public ResponseEntity<Object> deletar(@PathVariable String idBanca){
        try{
            bancaService.deletar(idBanca);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @PostMapping("/finalizar/{idBanca}")
    public ResponseEntity<Object> finalizarBanca(@PathVariable String idBanca){
        try{
            bancaService.finalizarBanca(idBanca);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}

