package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacao-banca")
@SuppressWarnings("unused")
public class NotificacaoBancasController {

    @Autowired
    private NoticacaoBancasService noticacaoBancasService;

    @GetMapping("/coordenadora/{idCoordenadora}")
    public ResponseEntity<List<NotificacaoBancas>> listarBancasNovas(@PathVariable String idCoordenadora){
        try{
            val notificacoes = noticacaoBancasService.listarBancasAdicionadas(idCoordenadora);
            return ResponseEntity.ok(notificacoes);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idNotificacao}")
    public ResponseEntity<NotificacaoBancas> encontrarPorId(@PathVariable String idNotificacao){
        try {
            val notificacao = noticacaoBancasService.encontrarPorId(idNotificacao);
            val notificacaoNaoExiste = !notificacao.isPresent();
            if(notificacaoNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(notificacao.get());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/visualizar/{idNotificacao}")
    public ResponseEntity<Boolean> visualizarNotificacao(@PathVariable String idNotificacao){
        try {
            boolean foiVisualizado = noticacaoBancasService.visualizarNotificacao(idNotificacao);
            return ResponseEntity.ok(foiVisualizado);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idNotificacao}")
    public ResponseEntity<Boolean> deletar(@PathVariable String idNotificacao){
        try {
            noticacaoBancasService.deletar(idNotificacao);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
