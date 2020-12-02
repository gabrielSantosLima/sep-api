package com.ifam.sistema_estagio.processes;

import java.util.*;

import com.ifam.sistema_estagio.dto.RespostaAprovacaoBancaDto;
import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import lombok.val;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.dto.BancaDto;

@Service
public class SolicitarBancaProcess {
	
	private final String ID_PROCESSO = "solicitar-banca";
	private final String NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO = "confirmarBancaMessage";
	private final String NOME_MENSAGEM_APROVAR_BANCA = "aprovarBancaMessage";
	public static final String VAR_BANCA = "banca";
	private final String VAR_APROVADO = "aprovado";
	private final String VAR_CONFIRMADO = "confirmado";
	private final String VAR_TOTAL_PARTICIPANTES = "totalParticipantes";
	private final String VAR_PARTICIPANTES_CONFIRMADOS = "participantesConfirmados";

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private BancaService bancaService;

	public Map<String, Object> iniciarProcesso(BancaDto banca) throws Exception {
		val variables = new HashMap<String, Object>();
		val response = new HashMap<String, Object>();

		if(banca == null) throw new Exception("Banca inválida");

		banca.getParticipantes().forEach(participante -> {
			String idParticipante = ManipularNumerosHexadecimais.numeroAleatorio();
			participante.setId(idParticipante);
			response.put(participante.getNome(), idParticipante);
		});

		variables.put(VAR_BANCA, banca);
		variables.put(VAR_TOTAL_PARTICIPANTES, banca.getParticipantes().size());
		variables.put(VAR_PARTICIPANTES_CONFIRMADOS, new ArrayList<String>());
		variables.put(VAR_CONFIRMADO, false);
		variables.put(VAR_APROVADO, false);

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ID_PROCESSO, variables);

		response.put("idProcesso", processInstance.getId());

		return response;
	}

	public long listarQuantidadeDeProcessos(){
		return taskService.createTaskQuery().processDefinitionKey(ID_PROCESSO).count();
	}

	public BancaDto listarBancaPorProcesso(String idProcesso) throws Exception {
		if(idProcesso.isEmpty()) throw new Exception("Id de processo inválido");
		val banca =  (BancaDto) retornarVariavel(idProcesso, VAR_BANCA);
		return banca;
	}

	public void confirmarParticipacao(String idProcesso, String idParticipante, Boolean resposta){
		val participantesConfirmados = (List<String>) retornarVariavel(idProcesso,VAR_PARTICIPANTES_CONFIRMADOS);
		val totalParticipantes = (Integer) retornarVariavel(idProcesso,VAR_TOTAL_PARTICIPANTES);
		val jaConfirmado = participantesConfirmados.contains(idParticipante);

		if(jaConfirmado) return;

		val naoConfirmou = !resposta;
		if(naoConfirmou){
			removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
			mudarVariavel(idProcesso, VAR_CONFIRMADO, false);
			enviarMensagem(NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO, idProcesso);
			return;
		}

		participantesConfirmados.add(idParticipante);

		mudarVariavel(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS, participantesConfirmados);

		if(totalParticipantes == participantesConfirmados.size()){
			removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
			mudarVariavel(idProcesso, VAR_CONFIRMADO, true);
			enviarMensagem(NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO, idProcesso);
		}
	}

	public void verificarAprovacaoBanca(String idProcesso,Boolean resposta){
		enviarResultadoBanca(idProcesso, resposta);
	}

	private void enviarResultadoBanca(String idProcesso, Boolean foiAprovado){
		mudarVariavel(idProcesso, VAR_APROVADO, foiAprovado);
		enviarMensagem(NOME_MENSAGEM_APROVAR_BANCA, idProcesso);
	}

	private void enviarMensagem(String nomeMensagem, String idProcesso){
		runtimeService.createMessageCorrelation(nomeMensagem)
				.processInstanceId(idProcesso)
				.correlate();
	}

	private void mudarVariavel(String idProcesso, String nome, Object valor){
		runtimeService.setVariable(idProcesso, nome, valor);
	}

	private Object retornarVariavel(String idProcesso, String nome){
		return runtimeService.getVariable(idProcesso, nome);
	}

	private void removerVariable(String idProcesso, String nome){
		runtimeService.removeVariable(idProcesso, nome);
	}
}
