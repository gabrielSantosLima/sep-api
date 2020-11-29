package com.ifam.sistema_estagio.controller;

import java.io.IOException;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.messages.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.reports.DocumentosManager;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/documentos")
@SuppressWarnings("unused")
public class DocumentosController {
	@Autowired
	private DocumentosManager documentosManager;

	private final CertificadoBuilderMessage certificadoBuilderMessage = new CertificadoBuilderMessage();
	private final FichaEstagioBuilderMessage fichaEstagioBuilderMessage = new FichaEstagioBuilderMessage();
	private final FichaProjetoCapaBuilderMessage fichaProjetoCapaBuilderMessage = new FichaProjetoCapaBuilderMessage();
	private final FichaProjetoDefesaBuilderMessage fichaProjetoDefesaBuilderMessage = new FichaProjetoDefesaBuilderMessage();
	private final FichaProjetoRelatorioBuilderMessage fichaProjetoRelatorioBuilderMessage = new FichaProjetoRelatorioBuilderMessage();
	private final CertificadoFrenteBuilderMessage certificadoFrenteBuilderMessage = new CertificadoFrenteBuilderMessage();
	private final AtaEstagioBuilderMessage ataEstagioBuilderMessage = new AtaEstagioBuilderMessage();
	private final AtaProjetoBuilderMessage ataProjetoBuilderMessage = new AtaProjetoBuilderMessage();

	@PostMapping(path = "/certificado", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCertificados(@RequestBody BancaDto banca) {
		byte[] pdf = {};
		try {
			val certificados = certificadoBuilderMessage.retornarMensagem(banca);
			val certificadosFrente = certificadoFrenteBuilderMessage.retornarMensagem(banca);
			pdf = documentosManager.gerarCertificado(certificados, certificadosFrente);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoEstagio(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			val fichas = fichaEstagioBuilderMessage.retornarMensagem(banca);
			pdf = documentosManager.gerarFichaDeAvaliacaoEstagio(fichas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoProjeto(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			val capas = fichaProjetoCapaBuilderMessage.retornarMensagem(banca);
			val defesas = fichaProjetoDefesaBuilderMessage.retornarMensagem(banca);
			val relatorios = fichaProjetoRelatorioBuilderMessage.retornarMensagem(banca);
			pdf = documentosManager.gerarFichaDeAvaliacaoProjeto(relatorios, defesas, capas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			val atas = ataEstagioBuilderMessage.retornarMensagem(banca);
			pdf = documentosManager.gerarAtaEstagio(atas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaProjeto(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			val atas = ataProjetoBuilderMessage.retornarMensagem(banca);
			pdf = documentosManager.gerarAtaProjeto(atas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}
}
