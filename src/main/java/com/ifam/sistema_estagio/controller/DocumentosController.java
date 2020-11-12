package com.ifam.sistema_estagio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.reports.messages.CertificadoBuilderMessage;
import com.ifam.sistema_estagio.reports.messages.FichaEstagioBuilderMessage;
import com.ifam.sistema_estagio.reports.messages.IBuilderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.reports.fields.AtaEstagioFields;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;
import com.ifam.sistema_estagio.reports.DocumentosService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

	@Autowired
	private DocumentosService service;

	private CertificadoBuilderMessage certificadoBuilderMessage = new CertificadoBuilderMessage();
	private FichaEstagioBuilderMessage fichaEstagioBuilderMessage = new FichaEstagioBuilderMessage();

	@PostMapping(path = "/certificado", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@RequestBody BancaDto banca) {
		byte[] pdf = {};
		try {
			List<CertificadoFields> certificados = certificadoBuilderMessage.retornarMensagem(banca);
			pdf = service.gerarCertificado(certificados);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoEstagio(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			List<FichaDeAvaliacaoEstagioFields> fichas = fichaEstagioBuilderMessage.retornarMensagem(banca);
			pdf = service.gerarFichaDeAvaliacaoEstagio(fichas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoProjeto() {
		byte pdf[] = null;
		try {
			pdf = service.gerarFichaDeAvaliacaoProjeto(null, null, null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio() {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaEstagio(null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaProjeto() {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaProjeto(null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}
}
