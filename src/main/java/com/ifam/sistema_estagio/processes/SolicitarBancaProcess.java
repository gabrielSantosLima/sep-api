package com.ifam.sistema_estagio.processes;

import java.util.*;

import com.ifam.sistema_estagio.dto.RespostaAprovacaoBancaDto;
import com.ifam.sistema_estagio.services.BancaService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.dto.BancaDto;

@Service
public class SolicitarBancaProcess {
	
	private final String ID_PROCESSO = "solicitar-banca";
	private final String NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO = "confirmarBancaMessage";
	private final String NOME_MENSAGEM_APROVAR_BANCA = "aprovarBancaMessage";
	public static final String VAR_BANCA = "banca";
	private final String VAR_APROVADO = "avaliado";
	private final String VAR_CONFIRMADO = "aprovado";
	private final String VAR_TOTAL_PARTICIPANTES = "totalParticipantes";
	private final String VAR_PARTICIPANTES_CONFIRMADOS = "participantesConfirmados";

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private BancaService bancaService;

	@Transactional
	public void iniciarProcesso(BancaDto banca) {
		Map<String, Object> variables = new HashMap<>();

		variables.put(VAR_BANCA, banca);
		variables.put(VAR_TOTAL_PARTICIPANTES, banca.getParticipantes().size());
		variables.put(VAR_PARTICIPANTES_CONFIRMADOS, new ArrayList<String>());
		variables.put(VAR_CONFIRMADO, false);
		variables.put(VAR_APROVADO, false);

		runtimeService.startProcessInstanceByKey(ID_PROCESSO, variables);
	}

	@Transactional
	public long listarProcessos(){
		return taskService.createTaskQuery().processDefinitionKey(ID_PROCESSO).count();
	}

	@Transactional
	public void confirmarParticipacao(String idProcesso, String idParticipante){
		List<String> participanteConfirmados = (List<String>) retornarVariavel(idProcesso,VAR_PARTICIPANTES_CONFIRMADOS);
		Integer totalParticipantes = (Integer) retornarVariavel(idProcesso,VAR_TOTAL_PARTICIPANTES);
		boolean jaConfirmado = participanteConfirmados.contains(idParticipante);

		if(jaConfirmado) return;

		participanteConfirmados.add(idParticipante);

		mudarVariavel(idProcesso,VAR_PARTICIPANTES_CONFIRMADOS,participanteConfirmados);
		mudarVariavel(idProcesso,VAR_TOTAL_PARTICIPANTES,participanteConfirmados.size());

		if(totalParticipantes == participanteConfirmados.size()){
			removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
			mudarVariavel(idProcesso, VAR_CONFIRMADO, true);
			enviarMensagem(NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO);
			return;
		}
	}

	@Transactional
	public void verificarAprovacaoBanca(String idProcesso,RespostaAprovacaoBancaDto resposta) throws Exception {
		Boolean naoFoiAprovado = !resposta.getAprovaBanca();

		if(naoFoiAprovado){
			negarBanca(idProcesso);
			return;
		}

		BancaDto banca = (BancaDto) retornarVariavel(idProcesso, VAR_BANCA);
		try{
			aprovarBanca(idProcesso, banca);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Transactional
	private void aprovarBanca(String idProcesso, BancaDto bancaDto) throws Exception {
		try{
			bancaService.create(bancaDto);
		}catch (Exception e){
			throw new Exception(e);
		}
		mudarVariavel(idProcesso, VAR_APROVADO, true);
		enviarMensagem(NOME_MENSAGEM_APROVAR_BANCA);
	}

	@Transactional
	private void negarBanca(String idProcesso){
		mudarVariavel(idProcesso, VAR_APROVADO, false);
		enviarMensagem(NOME_MENSAGEM_APROVAR_BANCA);
	}

	private void enviarMensagem(String nomeMensagem){
		runtimeService.createMessageCorrelation(nomeMensagem)
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
