package com.ifam.sistema_estagio.controller;

import java.util.List;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.reports.fields.*;
import com.ifam.sistema_estagio.reports.messages.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.reports.DocumentosManager;

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
	public ResponseEntity<Object> gerarCertificados(
			@RequestBody BancaDto banca,
			@RequestParam(required = false, defaultValue = "false") boolean emBranco
	) {
		try {
			List<CertificadoFields> certificados;
			List<FrenteCertificadoFields> certificadosFrente;

			if (emBranco) {
				certificados = certificadoBuilderMessage.retornarMensagemParaPreencher(banca);
				certificadosFrente = certificadoFrenteBuilderMessage.retornarMensagemParaPreencher(banca);
			} else {
				certificados = certificadoBuilderMessage.retornarMensagem(banca);
				certificadosFrente = certificadoFrenteBuilderMessage.retornarMensagem(banca);
			}
			val pdf = documentosManager.gerarCertificado(certificados, certificadosFrente);
			return ResponseEntity.ok(pdf);
		}catch(Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping(path = "/ficha-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Object> gerarFichaDeAvaliacaoEstagio(
			@RequestBody BancaDto banca,
			@RequestParam(required = false, defaultValue = "false") boolean emBranco
	) {
		try {
			List<FichaDeAvaliacaoEstagioFields> fichas;
			if (emBranco) {
				fichas = fichaEstagioBuilderMessage.retornarMensagemParaPreencher(banca);
			} else {
				fichas = fichaEstagioBuilderMessage.retornarMensagem(banca);
			}
			byte[] pdf = documentosManager.gerarFichaDeAvaliacaoEstagio(fichas);
			return ResponseEntity.ok(pdf);
		}catch(Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping(path = "/ficha-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Object> gerarFichaDeAvaliacaoProjeto(
			@RequestBody BancaDto banca,
			@RequestParam(required = false, defaultValue = "false") boolean emBranco
	) {
		try {
			List<FichaDeAvaliacaoProjetoCapaFields> capas;
			List<FichaDeAvaliacaoProjetoDefesaFields> defesas;
			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios;
			if (emBranco) {
				capas = fichaProjetoCapaBuilderMessage.retornarMensagemParaPreencher(banca);
				defesas = fichaProjetoDefesaBuilderMessage.retornarMensagemParaPreencher(banca);
				relatorios = fichaProjetoRelatorioBuilderMessage.retornarMensagemParaPreencher(banca);
			} else {
				capas = fichaProjetoCapaBuilderMessage.retornarMensagem(banca);
				defesas = fichaProjetoDefesaBuilderMessage.retornarMensagem(banca);
				relatorios = fichaProjetoRelatorioBuilderMessage.retornarMensagem(banca);
			}
			byte[] pdf = documentosManager.gerarFichaDeAvaliacaoProjeto(relatorios, defesas, capas);
			return ResponseEntity.ok(pdf);
		}catch(Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping(path = "/ata-estagio", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Object> geraAtaEstagio(
			@RequestBody BancaDto banca,
			@RequestParam(required = false, defaultValue = "false") boolean emBranco
	) {
		try {
			List<AtaEstagioFields> atas;
			if (emBranco) {
				atas = ataEstagioBuilderMessage.retornarMensagemParaPreencher(banca);
			} else {
				atas = ataEstagioBuilderMessage.retornarMensagem(banca);
			}
			byte[] pdf = documentosManager.gerarAtaEstagio(atas);
			return ResponseEntity.ok(pdf);
		}catch(Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping(path = "/ata-projeto", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Object> geraAtaProjeto(
			@RequestBody BancaDto banca,
			@RequestParam(required = false, defaultValue = "false") boolean emBranco
	) {
		try {
			List<AtaProjetoFields> atas;
			if (emBranco) {
				atas = ataProjetoBuilderMessage.retornarMensagemParaPreencher(banca);
			} else {
				atas = ataProjetoBuilderMessage.retornarMensagem(banca);
			}
			val pdf = documentosManager.gerarAtaProjeto(atas);
			return ResponseEntity.ok(pdf);
		}catch(Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}
}
