package com.ifam.sistema_estagio.controller.service.process;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;

public class SolicitarBancaEventListener implements ActivitiEventListener{
	
	@Override
	public void onEvent(ActivitiEvent event) {
		
		if(event.getType() == ActivitiEventType.PROCESS_STARTED) {
			System.out.println("Processo criado: " + event.getExecutionId());
		}
	}
	
	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
