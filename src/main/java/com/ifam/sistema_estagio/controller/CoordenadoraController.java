package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.services.CoordenadoraService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/coordenadores")
@SuppressWarnings("unused")
public class CoordenadoraController {

    @Autowired
    private CoordenadoraService coordenadoraService;

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            val coordenadoras = coordenadoraService.listar();
            return ResponseEntity.ok(coordenadoras);
        }catch(Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/{idCoordenadora}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idCoordenadora){
        try{
            val coordenadora = coordenadoraService.encontrarPorId(idCoordenadora);
            val coordenadoraNaoExiste = !coordenadora.isPresent();
            if(coordenadoraNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(coordenadora.get());
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
            val coordenadoras = coordenadoraService.encontrarPorNome(nome);
            return ResponseEntity.ok(coordenadoras);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}
