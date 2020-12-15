package com.ifam.sistema_estagio.exceptions;

import lombok.val;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;

public class ErroRequisicaoFactoryException{
    public static final Integer STATUS_CODE = 400;
    public static ResponseEntity<Object> construir(Exception e){
        val resposta = new HashMap<String, Object>();
        resposta.put("hora", new Date().toString());
        resposta.put("status", STATUS_CODE);
        resposta.put("mensagem", e.getMessage());
        return ResponseEntity.status(STATUS_CODE).body(resposta);
    }
}
