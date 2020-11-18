package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacao-banca")
public class NotificacaoBancasController {

    @Autowired
    private NoticacaoBancasService service;

    @GetMapping
    public List<NotificacaoBancas> listarBancasNovas(){
        List<NotificacaoBancas> notificacoes = service.listarBancasAdicionadas();
        return notificacoes;
    }

    @PostMapping("/{id}")
    public Boolean visualizarNotificacao(@PathVariable Integer id){
        try { return service.visualizarNotificacao(id); }
        catch (Exception e) { return false; }
    }
}
