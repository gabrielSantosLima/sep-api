package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.util.enums.Curso;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bancas")
@SuppressWarnings("unused")
public class BancaController {

    @Autowired
    private BancaService bancaService;

    @GetMapping
    public ResponseEntity<List<Banca>> listar(@RequestParam(required = false) Curso curso){
        try{
            val bancas = bancaService.listarPorCurso(curso);
            return ResponseEntity.ok(bancas);
        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idBanca}")
    public ResponseEntity<Banca> encontrarPorId(@PathVariable String idBanca){
        try{
            val banca = bancaService.encontrarPorId(idBanca);
            val bancaNaoExiste = !banca.isPresent();
            if(bancaNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(banca.get());
        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idBanca}")
    public ResponseEntity<Boolean> deletar(@PathVariable String idBanca){
        try{
            bancaService.deletar(idBanca);
            return ResponseEntity.ok(true);
        }catch (Exception error){
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/finalizar/{idBanca}")
    public ResponseEntity<Boolean> finalizarBanca(@PathVariable String idBanca){
        try{
            bancaService.finalizarBanca(idBanca);
            return ResponseEntity.ok(true);
        }catch (Exception error){
            return ResponseEntity.badRequest().body(false);
        }
    }
}

