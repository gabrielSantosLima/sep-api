package com.ifam.sistema_estagio.controller.processes;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/solicitar-banca")
public class SolicitarBancaController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@GetMapping(path = "/start")
	public void startProcesses() {
		runtimeService.startProcessInstanceById("processo");
	}

	@GetMapping(path = "/list")
	public String listProcesses() {
		return "Número de solicitação de processos em andamento: "+ runtimeService.createProcessInstanceQuery().count();
	}
}
