package com.ifam.sistema_estagio.processes.events;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service("avaliarPedidoListener")
public class AvaliarPedidoListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

    }
}
