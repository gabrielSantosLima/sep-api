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

    @GetMapping("/{idCoordenadora}")
    public ResponseEntity<List<NotificacaoBancas>> listarBancasNovas(@PathVariable String idCoordenadora){
        try{
            val notificacoes = noticacaoBancasService.listarBancasAdicionadas(idCoordenadora);
            return ResponseEntity.ok(notificacoes);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> visualizarNotificacao(@PathVariable String id){
        try {
            boolean foiVisualizado = noticacaoBancasService.visualizarNotificacao(id);
            return ResponseEntity.ok(foiVisualizado);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
