package com.ifam.sistema_estagio.processes.delegates;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.EmailSimplesDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;
import com.ifam.sistema_estagio.email.EmailSimplesService;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.Aplicacao;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("enviarEmailService")
public class EnviarEmailDelegate implements JavaDelegate{
	private final String ASSUNTO = "SEP - Sistema de Estágio e PCCT - Confirmação de participação";
	private final String NOME_SEM_AUTOR = "Sem definição";

	@Autowired
	private EmailSimplesService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String idInstance = execution.getProcessInstanceId();
		BancaDto banca = (BancaDto) execution.getVariable(SolicitarBancaProcess.VAR_BANCA);
		List<UsuarioDto> participantes = banca.getParticipantes();

		Optional<UsuarioDto> autor = participantes.stream()
				.filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
				.findFirst();

		String nomeAutor = autor.isPresent() ? autor.get().getNome() : NOME_SEM_AUTOR;
		String dataFormatada = FormatarData.porMascaraDataPadrao(banca.getData());
		String horaFormatada = FormatarData.porMascaraHoraPadrao(banca.getHoraInicio());
		String tipo = banca.getTipo().getValor().toLowerCase();
		String curso = banca.getCurso().retornarNomeCurso().toLowerCase();
		String titulo = banca.getEstagioPCCT().getTitulo();

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
		EmailSimplesDto email = new EmailSimplesDto();
		String mensagem = getMensagemBanca(
			new MensagemBanca(
					nomeAutor,
					idProcesso,
					titulo,
					curso,
					tipo,
					data,
					hora,
					participante
			)
		);

		email.setTo(participante.getEmail());
		email.setSubject(ASSUNTO);
		email.setMessage(mensagem);

		try{
			emailService.send(email);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private String getMensagemBanca(MensagemBanca mensagemBanca){
		return "Olá, " +
				mensagemBanca.getParticipante().getNome() +
				"\n" +
				"Você foi convidado para participar da banca de " +
				mensagemBanca.getTipo() +
				" do curso de " +
				mensagemBanca.getCurso() +
				" no dia " +
				mensagemBanca.getData() +
				" às " +
				mensagemBanca.getHora() +
				"\n" +
				"O título da apresentação será: " +
				mensagemBanca.getTitulo() + "." +
				"\n" +
				"Apresentado por: " +
				mensagemBanca.getNomeAutor() + "." +
				"\n\n" +
				"Confirme sua participação em: " +
				Aplicacao.BASE_URL.getValor() + "/solicitar-banca/confirmar-participacao/" +
				mensagemBanca.getIdProcesso() +
				"/" +
				mensagemBanca.getParticipante().getId();
	}
}

@Getter
@AllArgsConstructor
class MensagemBanca{
	private String nomeAutor;
	private String idProcesso;
	private String titulo;
	private String curso;
	private String tipo;
	private String data;
	private String hora;
	private UsuarioDto participante;
}
