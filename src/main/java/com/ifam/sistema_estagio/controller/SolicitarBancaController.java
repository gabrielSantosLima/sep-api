package com.ifam.sistema_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

@RestController
@RequestMapping("/solicitarbanca")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess process;
	
	@PostMapping
	public void start(@RequestBody BancaDto banca) {
		process.startProcess(banca);
	}

	@GetMapping(value="/listar")
	public long list() {
		return process.listProcesses();
	}
}
