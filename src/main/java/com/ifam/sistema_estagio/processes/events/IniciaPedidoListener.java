package com.ifam.sistema_estagio.processes.events;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

@Service("iniciaPedidoListener")
public class IniciaPedidoListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {

    }
}
