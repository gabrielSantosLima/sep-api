package com.ifam.sistema_estagio.processes.events;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;
import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.services.CoordenadoraService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pedidoFinalizadoAprovadoListener")
public class PedidoFinalizadoAprovadoListener implements ExecutionListener {

    @Autowired
    private BancaService bancaService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("[Banca Aprovada] Uma foi foi aprovada! Id do processo: "+ execution.getProcessInstanceId());
        BancaDto banca = (BancaDto) execution.getVariable(SolicitarBancaProcess.VAR_BANCA);
        try{
            bancaService.salvar(banca);
        }catch(Exception e){
            throw new Exception(e);
        }
        execution.removeVariables();
    }
}
