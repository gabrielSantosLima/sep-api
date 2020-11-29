package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.RespostaAprovacaoBancaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

import java.util.Map;

@RestController
@RequestMapping("/solicitar-banca")
@SuppressWarnings("unused")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess solicitarBancaProcess;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> iniciar(@RequestBody BancaDto banca) {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.iniciarProcesso(banca));
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<Long> listarQuantidadeDeProcessos() {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarQuantidadeDeProcessos());
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/retornar-banca/{idProcesso}")
	public ResponseEntity<BancaDto> listarBancaPorProcesso(@PathVariable String idProcesso){
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarBancaPorProcesso(idProcesso));
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/{idParticipante}")
	public ResponseEntity confirmarParticipacao(
			@PathVariable String idProcesso,
			@PathVariable String idParticipante
	){
		try{
			solicitarBancaProcess.confirmarParticipacao(idProcesso, idParticipante);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/aprovar-banca/{idProcesso}")
	public ResponseEntity aprovarBanca(
			@RequestBody RespostaAprovacaoBancaDto resposta,
			@PathVariable String idProcesso
	){
		try{
			solicitarBancaProcess.verificarAprovacaoBanca(idProcesso, resposta);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
