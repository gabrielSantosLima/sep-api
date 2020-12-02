package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.services.CoordenadoraService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ErroRequisicaoFactoryException.construir(e);
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
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/encontrar-nome")
    public ResponseEntity<Object> encontrarPorNome(@RequestParam String nome){
        try{
            val coordenadoras = coordenadoraService.encontrarPorNome(nome);
            return ResponseEntity.ok(coordenadoras);
        }catch (Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }
}
