package com.ifam.sistema_estagio.processes.events;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

@Service("pedidoCanceladoListener")
public class PedidoCanceladoListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("[Banca Cancelada] Uma foi foi cancelada! Id do processo: "+ execution.getProcessInstanceId());
        execution.removeVariables();
    }
}
