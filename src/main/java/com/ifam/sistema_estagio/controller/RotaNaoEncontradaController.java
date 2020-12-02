package com.ifam.sistema_estagio.controller;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/**")
@SuppressWarnings("unused")
public class RotaNaoEncontradaController {

    @GetMapping
    public ResponseEntity<Object> rotaNaoEncontradaGet(){
        val resposta = new HashMap<String, Object>();
        resposta.put("mensagem", "Rota não encontrada");
        return ResponseEntity.status(404).body(resposta);
    }

    @PostMapping
    public ResponseEntity<Object> rotaNaoEncontradaPost(){
        val resposta = new HashMap<String, Object>();
        resposta.put("mensagem", "Rota não encontrada");
        return ResponseEntity.status(404).body(resposta);
    }

    @PutMapping
    public ResponseEntity<Object> rotaNaoEncontradaPut(){
        val resposta = new HashMap<String, Object>();
        resposta.put("mensagem", "Rota não encontrada");
        return ResponseEntity.status(404).body(resposta);
    }

    @DeleteMapping
    public ResponseEntity<Object> rotaNaoEncontradaDelete(){
        val resposta = new HashMap<String, Object>();
        resposta.put("mensagem", "Rota não encontrada");
        return ResponseEntity.status(404).body(resposta);
    }

}
