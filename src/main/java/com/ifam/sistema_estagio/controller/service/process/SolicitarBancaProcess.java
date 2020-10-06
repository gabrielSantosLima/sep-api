package com.ifam.sistema_estagio.controller.service.process;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitarBancaProcess {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	private final String NOME_PROCESSO = "solicitar_banca";
	
	@Transactional
	public void startProcess() {
		runtimeService.startProcessInstanceByKey(NOME_PROCESSO);
	}

	public long listProcesses(){
		return taskService.createTaskQuery().count();
	}
}
