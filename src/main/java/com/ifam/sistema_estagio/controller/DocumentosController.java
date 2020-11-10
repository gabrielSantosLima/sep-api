package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifam.sistema_estagio.reports.fields.AtaEstagioFields;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;
import com.ifam.sistema_estagio.reports.DocumentosService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {

	@Autowired
	private DocumentosService service;

	@GetMapping(path = "/certificado/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@PathVariable Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarCertificado(new ArrayList<CertificadoFields>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@GetMapping(path = "/ficha-estagio/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoEstagio(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarFichaDeAvaliacaoEstagio(null); // TODO Buscar por ficha de estágio
		} catch (JRException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@GetMapping(path = "/ficha-projeto/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoProjeto(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarFichaDeAvaliacaoProjeto(null, null, null);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@GetMapping(path = "/ata-estagio/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaEstagio(null);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@GetMapping(path = "/ata-projeto/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaProjeto(@PathVariable("id") Integer id) {
		byte pdf[] = null;
		try {
			pdf = service.gerarAtaProjeto(null);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return pdf;
	}
}
