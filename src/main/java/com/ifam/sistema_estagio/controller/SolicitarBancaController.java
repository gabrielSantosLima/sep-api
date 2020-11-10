package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.RespostaAprovacaoBancaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

@RestController
@RequestMapping("/solicitar-banca")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess solicitarBancaProcess;
	
	@PostMapping
	public void iniciar(@RequestBody BancaDto banca) {
		solicitarBancaProcess.iniciarProcesso(banca);
	}

	@GetMapping("/listar")
	public long listar() {
		return solicitarBancaProcess.listarProcessos();
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/{idParticipante}")
	public void confirmarParticipacao( @PathVariable String idProcesso,@PathVariable String idParticipante){
		solicitarBancaProcess.confirmarParticipacao(idProcesso, idParticipante);
	}

	@PostMapping("/aprovar-banca/{idProcesso}")
	public ResponseEntity aprovarBanca(
			@RequestBody RespostaAprovacaoBancaDto resposta,
			@PathVariable String idProcesso
	){
		try{
			solicitarBancaProcess.verificarAprovacaoBanca(idProcesso, resposta);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}
