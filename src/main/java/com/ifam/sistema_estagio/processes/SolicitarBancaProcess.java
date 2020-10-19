package com.ifam.sistema_estagio.processes;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.dto.BancaDto;

@Service
public class SolicitarBancaProcess {
	
	private final String ID_PROCESSO = "solicitar-banca";
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Transactional
	public void startProcess(BancaDto banca) {
		Map<String, Object> variables = new HashMap<>();
		
		variables.put("banca", banca);
		runtimeService.startProcessInstanceById(ID_PROCESSO, variables);
	}

	public long listProcesses(){
		return 0;
	}
}
