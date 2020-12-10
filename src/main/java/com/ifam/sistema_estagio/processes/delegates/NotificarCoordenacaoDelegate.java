package com.ifam.sistema_estagio.processes.delegates;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;
import com.ifam.sistema_estagio.services.CoordenadoraService;
import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import com.ifam.sistema_estagio.util.FormatarData;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("notificarCoordenacaoService")
public class NotificarCoordenacaoDelegate implements JavaDelegate {

    @Autowired
    private CoordenadoraService coordenadoraService;

    @Autowired
    private NoticacaoBancasService noticacaoBancasService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        val idProcesso = execution.getProcessInstanceId();
        val resultado = execution.getVariable(SolicitarBancaProcess.VAR_BANCA);

        if(!(resultado instanceof BancaDto)) throw new Exception("Erro ao notificar banca!");

        val banca = (BancaDto) resultado;

        val coordenadoraDto = banca.getCoordenadora();

        val idCoordenadora = coordenadoraDto.getId();
        val coordenadora = coordenadoraService.encontrarPorId(idCoordenadora);
        val coordenadorNaoEncontrado = !coordenadora.isPresent();
        if(coordenadorNaoEncontrado) throw new Exception("Coordenadora não existe.");

        val dataNotificacao = FormatarData.porMascaraDataPadrao(new Date());
        noticacaoBancasService.salvar(NotificacaoBancas.builder()
                .dataNotificacao(dataNotificacao)
                .jaVisualizado(false)
                .idProcesso(idProcesso)
                .coordenadora(coordenadora.get())
                .build()
        );
    }
}
