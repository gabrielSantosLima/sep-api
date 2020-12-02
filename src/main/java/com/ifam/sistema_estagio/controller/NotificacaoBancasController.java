package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/notificacao-banca")
@SuppressWarnings("unused")
public class NotificacaoBancasController {

    @Autowired
    private NoticacaoBancasService noticacaoBancasService;

    @GetMapping("/coordenadora/{idCoordenadora}")
    public ResponseEntity<Object> listarBancasNovas(@PathVariable String idCoordenadora){
        try{
            val notificacoes = noticacaoBancasService.listarBancasAdicionadas(idCoordenadora);
            return ResponseEntity.ok(notificacoes);
        }catch (Exception e){
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @GetMapping("/{idNotificacao}")
    public ResponseEntity<Object> encontrarPorId(@PathVariable String idNotificacao){
        try {
            val notificacao = noticacaoBancasService.encontrarPorId(idNotificacao);
            val notificacaoNaoExiste = !notificacao.isPresent();
            if(notificacaoNaoExiste) return ResponseEntity.ok().build();
            return ResponseEntity.ok(notificacao.get());
        }catch (Exception e) {
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @PostMapping("/visualizar/{idNotificacao}")
    public ResponseEntity<Object> visualizarNotificacao(@PathVariable String idNotificacao){
        try {
            boolean foiVisualizado = noticacaoBancasService.visualizarNotificacao(idNotificacao);
            return ResponseEntity.ok(foiVisualizado);
        }catch (Exception e) {
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @DeleteMapping("/{idNotificacao}")
    public ResponseEntity<Object> deletar(@PathVariable String idNotificacao){
        try {
            noticacaoBancasService.deletar(idNotificacao);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}
