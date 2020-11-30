package com.ifam.sistema_estagio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/**")
public class RotaNaoEncontradaController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> rotaNaoEncontrada(){
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Rota não encontrada");
        return ResponseEntity.status(404).body(resposta);
    }

}
