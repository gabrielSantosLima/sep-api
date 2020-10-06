package com.ifam.sistema_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.controller.service.process.SolicitarBancaProcess;

@RestController
@RequestMapping("/process")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess process;
	
	@GetMapping(value="/start")
	public void start() {
		process.startProcess();
	}

	@GetMapping(value="/list")
	public long list() {
		return process.listProcesses();
	}
}
