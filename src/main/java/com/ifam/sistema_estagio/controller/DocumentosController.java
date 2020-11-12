package com.ifam.sistema_estagio.controller;

import java.io.IOException;
import java.util.List;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.*;
import com.ifam.sistema_estagio.reports.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.reports.DocumentosService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

	@Autowired
	private DocumentosService service;

	private CertificadoBuilderMessage certificadoBuilderMessage = new CertificadoBuilderMessage();
	private FichaEstagioBuilderMessage fichaEstagioBuilderMessage = new FichaEstagioBuilderMessage();
	private FichaProjetoCapaBuilderMessage fichaProjetoCapaBuilderMessage = new FichaProjetoCapaBuilderMessage();
	private FichaProjetoDefesaBuilderMessage fichaProjetoDefesaBuilderMessage = new FichaProjetoDefesaBuilderMessage();
	private FichaProjetoRelatorioBuilderMessage fichaProjetoRelatorioBuilderMessage = new FichaProjetoRelatorioBuilderMessage();
	private CertificadoFrenteBuilderMessage certificadoFrenteBuilderMessage = new CertificadoFrenteBuilderMessage();
	private AtaEstagioBuilderMessage ataEstagioBuilderMessage = new AtaEstagioBuilderMessage();

	@PostMapping(path = "/certificado", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@RequestBody BancaDto banca) {
		byte[] pdf = {};
		try {
			List<CertificadoFields> certificados = certificadoBuilderMessage.retornarMensagem(banca);
			List<FrenteCertificadoFields> certificadosFrente = certificadoFrenteBuilderMessage.retornarMensagem(banca);

			pdf = service.gerarCertificado(certificados, certificadosFrente);
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
	public byte[] gerarFichaDeAvaliacaoProjeto(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			List<FichaDeAvaliacaoProjetoCapaFields> capas = fichaProjetoCapaBuilderMessage.retornarMensagem(banca);
			List<FichaDeAvaliacaoProjetoDefesaFields> defesas = fichaProjetoDefesaBuilderMessage.retornarMensagem(banca);
			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios = fichaProjetoRelatorioBuilderMessage.retornarMensagem(banca);

			pdf = service.gerarFichaDeAvaliacaoProjeto(relatorios, defesas, capas);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	@PostMapping(path = "/ata-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio(@RequestBody BancaDto banca) {
		byte pdf[] = null;
		try {
			List<AtaEstagioFields> atas = ataEstagioBuilderMessage.retornarMensagem(banca);
			pdf = service.gerarAtaEstagio(atas);
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
