package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao-banca")
@SuppressWarnings("unused")
public class NotificacaoBancasController {

    @Autowired
    private NoticacaoBancasService noticacaoBancasService;

    @GetMapping
    public ResponseEntity<Object> listar(
            @RequestParam(required = false, defaultValue = "false") boolean jaVisualizado
    ){
        try{
            val notificacoes = noticacaoBancasService.listar(jaVisualizado);
            return ResponseEntity.ok(notificacoes);
        }catch(Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/coordenadora/{idCoordenadora}")
    public ResponseEntity<Object> listarNotificacoesNaoVisualizadas(
            @PathVariable String idCoordenadora,
            @RequestParam(required = false, defaultValue = "false") boolean jaVisualizado
    ){
        try{
            val notificacoes = noticacaoBancasService.listar(idCoordenadora,jaVisualizado);
            return ResponseEntity.ok(notificacoes);
        }catch (Exception e){
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @GetMapping("/{idNotificacao}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idNotificacao){
        try {
            val notificacao = noticacaoBancasService.encontrarPorId(idNotificacao);
            val notificacaoNaoExiste = !notificacao.isPresent();
            if(notificacaoNaoExiste) throw new Exception("Notificação da banca não encontrada");
            return ResponseEntity.ok(notificacao.get());
        }catch (Exception e) {
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @PostMapping("/visualizar/{idNotificacao}")
    public ResponseEntity<Object> visualizarNotificacao(@PathVariable String idNotificacao){
        try {
            val notificacaoVisualizada = noticacaoBancasService.visualizarNotificacao(idNotificacao);
            return ResponseEntity.ok(notificacaoVisualizada);
        }catch (Exception e) {
            return ErroRequisicaoFactoryException.construir(e);
        }
    }

    @DeleteMapping("/{idNotificacao}")
    public ResponseEntity<Object> deletar(@PathVariable String idNotificacao){
        try {
            noticacaoBancasService.deletar(idNotificacao);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            return ErroRequisicaoFactoryException.construir(e);
        }
    }
}
