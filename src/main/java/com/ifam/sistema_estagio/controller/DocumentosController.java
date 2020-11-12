package com.ifam.sistema_estagio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.messages.CertificadoBuilderMessage;
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

	@PostMapping(path = "/certificado/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@RequestBody BancaDto banca, @PathVariable Integer id) {
		byte[] pdf = {};
		try {
			List<CertificadoFields> certificados = certificadoBuilderMessage.retornarMensagem(banca);
			pdf = service.gerarCertificado(certificados);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-estagio/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoEstagio(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarFichaDeAvaliacaoEstagio(null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ficha-projeto/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoProjeto(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarFichaDeAvaliacaoProjeto(null, null, null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-estagio/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaEstagio(null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-projeto/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaProjeto(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaProjeto(null);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}
}
