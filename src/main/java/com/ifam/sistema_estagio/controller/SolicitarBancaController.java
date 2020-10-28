package com.ifam.sistema_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

@RestController
@RequestMapping("/solicitar-banca")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess solicitarBancaProcess;
	
	@PostMapping
	public void start(@RequestBody BancaDto banca) {
		solicitarBancaProcess.iniciarProcesso(banca);
	}

	@GetMapping("/listar")
	public long list() {
		return solicitarBancaProcess.listarProcessos();
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/idParticipante")
	public void confirmarParticipacao(@PathVariable String idProcesso,
									  @PathVariable String idParticipante
	){
		solicitarBancaProcess.confirmarParticipacao(idProcesso, idParticipante);
	}
}
