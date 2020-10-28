package com.ifam.sistema_estagio.processes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.util.RandomHex;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.dto.BancaDto;

@Service
public class SolicitarBancaProcess {
	
	private final String ID_PROCESSO = "solicitar-banca";
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Transactional
	public void iniciarProcesso(BancaDto banca) {
		Map<String, Object> variables = new HashMap<>();

		String randomId = RandomHex.generateRandomHex();

		banca.setId(randomId);
		banca.getParticipantes().forEach(participante -> participante.setId(randomId));

		variables.put("banca", banca);
		variables.put("totalParticipantes", banca.getParticipantes().size());
		variables.put("participantesConfirmados", new ArrayList<String>());
		variables.put("aprovado", false);
		variables.put("avaliado", false);

		runtimeService.startProcessInstanceByKey(ID_PROCESSO, variables);
	}

	@Transactional
	public long listarProcessos(){
		return taskService.createTaskQuery().count();
	}

	@Transactional
	public void confirmarParticipacao(String idProcesso, String idPartipante){
		List<String> participanteConfirmados = (List<String>) runtimeService.getVariable(idProcesso,
				"participantesConfirmados");
		Integer totalParticipantes = (Integer) runtimeService.getVariable(idProcesso,
				"totalParticipantes");
		boolean jaConfirmado = participanteConfirmados.contains(idPartipante);

		if(jaConfirmado) return;

		participanteConfirmados.add(idPartipante);

		if(totalParticipantes == participanteConfirmados.size()){
			runtimeService.removeVariable(idProcesso, "participantesConfirmados");
			runtimeService.setVariable(idProcesso, "aprovado", true);
			runtimeService.activateProcessInstanceById(idProcesso);
		}

		runtimeService.setVariable(idProcesso,
				"participantesConfirmados",
				participanteConfirmados
		);
		runtimeService.setVariable(idProcesso,
				"totalParticipantes",
				participanteConfirmados.size()
		);
	}

}
