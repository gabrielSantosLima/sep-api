package com.ifam.sistema_estagio.processes.delegates;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.EmailSimplesDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.services.email.EmailTextoService;
import com.ifam.sistema_estagio.util.enums.Aplicacao;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("enviarEmailService")
public class EnviarEmailDelegate implements JavaDelegate{

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private EmailTextoService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String idInstance = execution.getProcessInstanceId();

		BancaDto banca = (BancaDto) execution.getVariable("banca");
		List<UsuarioDto> participantes = banca.getParticipantes();
		String subject = "SEP - Sistema de Estágio e PCCT - Confirmação de participação";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date data = banca.getData();

		String dataFormatada = simpleDateFormat.format(data);

		participantes.forEach(participante -> {
			EmailSimplesDto email = new EmailSimplesDto();

			String mensagem = "Olá, " +
					participante.getNome() +
					"\n" +
					"Você foi convidado para participar da banca de " +
					banca.getTipo().getValor().toLowerCase() +
					" do curso de " +
					banca.getCurso().getNomeCurso().toLowerCase() +
					"no dia " +
					dataFormatada +
					"\n" +
					"O tema da apresentação será: " +
					banca.getTitulo() + "." +
					"\n\n" +
					"Confirme sua participação em: " +
					Aplicacao.BASE_URL.getValor() + "/confirmar-participacao/" +
					execution.getId() +
					"/" +
					participante.getId();

			email.setTo(participante.getEmail());
			email.setSubject(subject);
			email.setMessage(mensagem);

			//emailService.send(email);
		});

		runtimeService.suspendProcessInstanceById(idInstance);
	}

}
