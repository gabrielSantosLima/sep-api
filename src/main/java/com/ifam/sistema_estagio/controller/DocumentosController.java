package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifam.sistema_estagio.reports.AtaEstagioFields;
import com.ifam.sistema_estagio.reports.AtaProjetoFields;
import com.ifam.sistema_estagio.reports.CertificadoFields;
import com.ifam.sistema_estagio.reports.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.FichaDeAvaliacaoProjetoRelatorioFields;
import com.ifam.sistema_estagio.services.AvaliadoresService;
import com.ifam.sistema_estagio.services.BancaService;
import com.ifam.sistema_estagio.services.DocumentosService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {

	@Autowired
	private DocumentosService service;

	@GetMapping(path = "/certificado/{idBanca}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@PathVariable Integer idBanca) {
		try {

			// Exporta pdf
			byte[] pdf = service.generateCertificado(new ArrayList<CertificadoFields>());

			return pdf;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@GetMapping(path = "/professor/{id}/ficha-estagio/{idFicha}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoEstagio(@PathVariable("id") Integer id,
			@PathVariable("idFicha") Integer idFicha) {
		try {
			List<FichaDeAvaliacaoEstagioFields> fichas = new ArrayList<>();
			fichas.add(new FichaDeAvaliacaoEstagioFields());
			fichas.add(new FichaDeAvaliacaoEstagioFields());
			fichas.add(new FichaDeAvaliacaoEstagioFields());

			byte[] pdf = service.generateFichaDeAvaliacaoEstagio(fichas);

			return pdf;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(path = "/professor/{id}/ficha-projeto/{idFicha}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarFichaDeAvaliacaoProjeto(@PathVariable("id") Integer id,
			@PathVariable("idFicha") Integer idFicha) {
		try {
			List<FichaDeAvaliacaoProjetoCapaFields> capas = new ArrayList<>();
			capas.add(new FichaDeAvaliacaoProjetoCapaFields());

			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios = new ArrayList<>();
			relatorios.add(new FichaDeAvaliacaoProjetoRelatorioFields());
			relatorios.add(new FichaDeAvaliacaoProjetoRelatorioFields());
			relatorios.add(new FichaDeAvaliacaoProjetoRelatorioFields());

			List<FichaDeAvaliacaoProjetoDefesaFields> defesas = new ArrayList<>();
			defesas.add(new FichaDeAvaliacaoProjetoDefesaFields());
			defesas.add(new FichaDeAvaliacaoProjetoDefesaFields());
			defesas.add(new FichaDeAvaliacaoProjetoDefesaFields());

			byte[] pdf = service.generateFichaDeAvaliacaoProjeto(relatorios, defesas,
					capas);

			return pdf;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(path = "/banca/{id}/ata-estagio/{idAta}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaEstagio(@PathVariable("id") Integer id,
			@PathVariable("idAta") Integer idAta) {
		try {
			List<AtaEstagioFields> atas = new ArrayList<>();
			atas.add(new AtaEstagioFields());
			atas.add(new AtaEstagioFields());
			atas.add(new AtaEstagioFields());

			byte[] pdf = service.generateAtaEstagio(atas);

			return pdf;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(path = "/banca/{id}/ata-projeto/{idAta}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] geraAtaProjeto(@PathVariable("id") Integer id,
			@PathVariable("idAta") Integer idAta) {
		try {
			List<AtaProjetoFields> atas = new ArrayList<>();
			atas.add(new AtaProjetoFields());
			atas.add(new AtaProjetoFields());
			atas.add(new AtaProjetoFields());

			byte[] pdf = service.generateAtaProjeto(atas);

			return pdf;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}
}
