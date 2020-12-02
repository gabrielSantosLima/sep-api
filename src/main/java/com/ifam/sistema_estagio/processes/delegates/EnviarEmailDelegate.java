package com.ifam.sistema_estagio.processes.delegates;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.EmailSimplesDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.email.EmailHtmlService;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.Aplicacao;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("enviarEmailService")
public class EnviarEmailDelegate implements JavaDelegate{
	private final String ASSUNTO = "SEP - Sistema de Estágio e PCCT - Confirmação de participação";
	private final String NOME_SEM_AUTOR = "Sem definição";
	private final String ROTA_CONFIRMACAO_PARTICIPACAO = "/solicitar-banca/confirmar-participacao/";

	@Autowired
	private EmailHtmlService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		val idInstance = execution.getProcessInstanceId();
		val banca = (BancaDto) execution.getVariable(SolicitarBancaProcess.VAR_BANCA);
		val participantes = banca.getParticipantes();

		val autor = participantes.stream()
				.filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
				.findFirst();

		val nomeAutor = autor.isPresent() ? autor.get().getNome() : NOME_SEM_AUTOR;
		val dataFormatada = FormatarData.porMascaraDataPadrao(banca.getData());
		val horaFormatada = FormatarData.porMascaraHoraPadrao(banca.getHoraInicio());
		val tipo = banca.getTipo().getValor().toLowerCase();
		val curso = banca.getCurso().retornarNomeCurso().toLowerCase();
		val titulo = banca.getEstagioPCCT().getTitulo();

		participantes.forEach(participante -> {
			enviarEmail(
					nomeAutor,
					participante,
					idInstance,
					titulo,
					curso,
					tipo,
					dataFormatada,
					horaFormatada
			);
		});
	}

	private void enviarEmail(
			String nomeAutor,
			UsuarioDto participante,
			String idProcesso,
			String titulo,
			String curso,
			String tipo,
			String data,
			String hora
	){
		val email = new EmailSimplesDto();
		email.setTo(participante.getEmail());
		email.setSubject(ASSUNTO);

		val params = new HashMap<String, Object>();
		params.put("nome", participante.getNome());
		params.put("tipo",tipo);
		params.put("curso",curso);
		params.put("data", data);
		params.put("horario",hora);
		params.put("titulo",titulo);
		params.put("autor",nomeAutor);
		params.put("url",
				Aplicacao.BASE_URL.getValor() + ROTA_CONFIRMACAO_PARTICIPACAO +
				idProcesso + "/" +
				participante.getId()
		);
		email.setParams(params);
		try{
			// TODO ativar novamente quando tiver internet
//			emailService.send(email);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
