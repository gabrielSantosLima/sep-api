package com.ifam.sistema_estagio.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

import java.util.HashMap;

@RestController
@RequestMapping("/solicitar-banca")
@SuppressWarnings("unused")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess solicitarBancaProcess;
	
	@PostMapping
	public ResponseEntity<Object> iniciar(@RequestBody BancaDto banca) {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.iniciarProcesso(banca));
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listarQuantidadeDeProcessos() {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarQuantidadeDeProcessos());
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/retornar-banca/{idProcesso}")
	public ResponseEntity<Object> listarBancaPorProcesso(@PathVariable String idProcesso){
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarBancaPorProcesso(idProcesso));
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/{idParticipante}")
	public ResponseEntity<Object> confirmarParticipacao(
			@PathVariable String idProcesso,
			@PathVariable String idParticipante,
			@RequestParam Boolean resposta
	){
		try{
			solicitarBancaProcess.confirmarParticipacao(idProcesso, idParticipante, resposta);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/aprovar-banca/{idProcesso}")
	public ResponseEntity<Object> aprovarBanca(
			@PathVariable String idProcesso,
			@RequestParam	 Boolean resposta
	){
		try{
			solicitarBancaProcess.verificarAprovacaoBanca(idProcesso, resposta);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}
}
