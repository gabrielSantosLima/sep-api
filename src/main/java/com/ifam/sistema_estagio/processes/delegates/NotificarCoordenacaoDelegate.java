package com.ifam.sistema_estagio.processes.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("notificarCoordenacaoService")
public class NotificarCoordenacaoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
