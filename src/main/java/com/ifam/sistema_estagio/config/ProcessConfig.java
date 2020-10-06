package com.ifam.sistema_estagio.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ifam.sistema_estagio.controller.service.process.SolicitarBancaEventListener;

@Configuration
public class ProcessConfig{
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Bean
	public void config() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource("processes/solicitar-banca.bpmn")
		  .deploy();
	}
	
	@Bean
	public void addEvents() {
		runtimeService.addEventListener(new SolicitarBancaEventListener());
	}
}
