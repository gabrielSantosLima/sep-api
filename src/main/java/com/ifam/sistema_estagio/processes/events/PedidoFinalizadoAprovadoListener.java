package com.ifam.sistema_estagio.processes.events;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

@Service("pedidoFinalizadoAprovadoListener")
public class PedidoFinalizadoAprovadoListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("[Banca Aprovada] Uma foi foi aprovada! Id do processo: "+ execution.getProcessInstanceId());
    }
}
