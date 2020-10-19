package com.ifam.sistema_estagio.processes.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("enviarEmailService")
public class EnviarEmailDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	}

}
