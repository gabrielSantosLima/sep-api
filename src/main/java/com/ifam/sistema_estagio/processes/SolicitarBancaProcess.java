package com.ifam.sistema_estagio.processes;

import java.util.*;

import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import lombok.NonNull;
import lombok.val;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.dto.BancaDto;

import javax.transaction.Transactional;

@Service
public class SolicitarBancaProcess {
	
	private final String NOME_ID_PROCESSO = "solicitar-banca";
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

	@Transactional
	public Map<String, Object> iniciarProcesso(BancaDto banca) throws Exception {
		if(banca == null) throw new Exception("Banca inválida");

		val estagioSemCoordenador = banca.getParticipantes().stream()
				.filter(participante -> participante.getFuncao() == FuncaoEstagio.COORDENADOR)
				.count() == 0;
		val estagioSemOrientador = banca.getParticipantes().stream()
				.filter(participante -> participante.getFuncao() == FuncaoEstagio.ORIENTADOR)
				.count() == 0;
		if (estagioSemCoordenador) throw new Exception("Banca sem nenhum coordenador(a)");
		if (estagioSemOrientador) throw new Exception("Banca sem nenhum orientador(a)");

		val params = new HashMap<String, Object>();
		params.put(VAR_BANCA, banca);
		params.put(VAR_TOTAL_PARTICIPANTES, banca.getParticipantes().size());
		params.put(VAR_PARTICIPANTES_CONFIRMADOS, new ArrayList<String>());
		params.put(VAR_CONFIRMADO, false);
		params.put(VAR_APROVADO, false);

		val processInstance = runtimeService.startProcessInstanceByKey(
				NOME_ID_PROCESSO,
				params
		);

		val idParticipantes = new HashMap<String, Object>();
		banca.getParticipantes().forEach(participante -> {
			idParticipantes.put(participante.getNome(), participante.getId());
		});

		val resposta = new HashMap<String, Object>();
		resposta.put("idProcesso", processInstance.getId());
		resposta.put("idParticipantes", idParticipantes);
		resposta.put("criadoEm", new Date());
		return resposta;
	}

	@Transactional
	public long listarQuantidadeDeProcessos(){
		return runtimeService.createExecutionQuery().processInstanceId(NOME_ID_PROCESSO).count();
	}

	@Transactional
	public BancaDto listarBancaPorProcesso(@NonNull String idProcesso) throws Exception {
		if(idProcesso.isEmpty()) throw new Exception("Id de processo inválido");
		val banca =  (BancaDto) retornarVariavel(idProcesso, VAR_BANCA);
		return banca;
	}

	@Transactional
	public void confirmarParticipacao(@NonNull String idProcesso, String idParticipante, Boolean resposta) throws Exception {
		val participantesConfirmados = (List<String>) retornarVariavel(
				idProcesso,
				VAR_PARTICIPANTES_CONFIRMADOS
		);
		val totalParticipantes = (Integer) retornarVariavel(idProcesso,VAR_TOTAL_PARTICIPANTES);
		val jaConfirmado = participantesConfirmados.contains(idParticipante);

		if(jaConfirmado) throw new Exception("Participante já confirmou/cancelou sua participação");

		val naoConfirmou = !resposta;
		if(naoConfirmou){
			removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
			mudarVariavel(idProcesso, VAR_CONFIRMADO, false);
			enviarMensagem(idProcesso, NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO);
			return;
		}

		participantesConfirmados.add(idParticipante);

		mudarVariavel(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS, participantesConfirmados);

		if(totalParticipantes == participantesConfirmados.size()){
			removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
			mudarVariavel(idProcesso, VAR_CONFIRMADO, true);
			enviarMensagem(idProcesso, NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO);
		}
	}

	@Transactional
	public void confirmarParticipacaoTodos(@NonNull String idProcesso) {
		removerVariable(idProcesso, VAR_PARTICIPANTES_CONFIRMADOS);
		mudarVariavel(idProcesso, VAR_CONFIRMADO, true);
		enviarMensagem(idProcesso, NOME_MENSAGEM_CONFIRMAR_PARTICIPACAO);
	}

	@Transactional
	public void verificarAprovacaoBanca(@NonNull String idProcesso,Boolean resposta){
		enviarResultadoBanca(idProcesso, resposta);
	}

	@Transactional
	private void enviarResultadoBanca(@NonNull String idProcesso, Boolean foiAprovado){
		mudarVariavel(idProcesso, VAR_APROVADO, foiAprovado);
		enviarMensagem(idProcesso, NOME_MENSAGEM_APROVAR_BANCA);
	}

	@Transactional
	private void enviarMensagem(String idProcesso, String nomeMensagem){
		runtimeService.createMessageCorrelation(nomeMensagem)
				.processInstanceId(idProcesso)
				.correlate();
	}

	@Transactional
	private void mudarVariavel(String idProcesso, String nome, Object valor){
		runtimeService.setVariable(idProcesso, nome, valor);
	}

	@Transactional
	private Object retornarVariavel(String idProcesso, String nome){
		return runtimeService.getVariable(idProcesso, nome);
	}

	@Transactional
	private void removerVariable(String idProcesso, String nome){
		runtimeService.removeVariable(idProcesso, nome);
	}
}
