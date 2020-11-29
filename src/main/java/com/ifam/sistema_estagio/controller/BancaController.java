package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.util.enums.Curso;
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
            List<Banca> bancas = bancaService.listarPorCurso(curso);
            return ResponseEntity.ok(bancas);
        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/finalizar-banca/{idBanca}")
    public ResponseEntity<Boolean> finalizarBanca(@PathVariable String idBanca){
        try{
            bancaService.finalizarBanca(idBanca);
            return ResponseEntity.ok(true);
        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }
}

