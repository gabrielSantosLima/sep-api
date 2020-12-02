package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/**")
@SuppressWarnings("unused")
public class RotaNaoEncontradaController {

    @GetMapping
    public ResponseEntity<Object> rotaNaoEncontradaGet(){
        return ErroRequisicaoFactoryException.construir(new Exception("Rota não encontrada."));
    }

    @PostMapping
    public ResponseEntity<Object> rotaNaoEncontradaPost(){
        return ErroRequisicaoFactoryException.construir(new Exception("Rota não encontrada."));
    }

    @PutMapping
    public ResponseEntity<Object> rotaNaoEncontradaPut(){
        return ErroRequisicaoFactoryException.construir(new Exception("Rota não encontrada."));
    }

    @DeleteMapping
    public ResponseEntity<Object> rotaNaoEncontradaDelete(){
        return ErroRequisicaoFactoryException.construir(new Exception("Rota não encontrada."));
    }

}
