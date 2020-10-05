package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifam.sistema_estagio.controller.service.AvaliadoresService;
import com.ifam.sistema_estagio.controller.service.BancaService;
import com.ifam.sistema_estagio.controller.service.DocumentosService;
import com.ifam.sistema_estagio.model.entity.Avaliadores;
import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.reports.fields.AtaEstagioFields;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {

	@Autowired
	private DocumentosService service;

	@Autowired
	private BancaService bancaService;

	@Autowired
	private AvaliadoresService avaliadoresService;

	@GetMapping(path = "/certificado/{idBanca}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarCerticados(@PathVariable Integer idBanca) {
		try {
			// Prepara Fields
			List<CertificadoFields> certificados = new ArrayList<>();
			Optional<Banca> banca = bancaService.findById(idBanca);

			if (!banca.isPresent()) {
				return null;
			}

			List<Avaliadores> avaliadores = avaliadoresService.findByBancaId(idBanca);

			if (avaliadores.isEmpty()) {
				return null;
			}

			// Processa dados
			avaliadores.forEach(avaliador -> {
				Banca bancaValida = banca.get();

				String nomeAvaliador = avaliador.getProfessor().getNome();

				String papelAvaliador = avaliador.getProfessor().getTipo().toString()
						.toLowerCase();

				String alunos = bancaValida.getEstagioPcct().getAlunos().toString();

				String data = bancaValida.getData().toString();

				String curso = bancaValida.getCurso().getNomeCursoCompleto();

				String cood = "Coordenação do Curso de "
						+ bancaValida.getCurso().getNomeCoordenacao()
						+ " do Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM";

				String mensagem = "Certificamos para os devidos fins de direito que o Prof. "
						+ nomeAvaliador + "participou como " + papelAvaliador
						+ "na banca de defesa do Trabalho de Conclusão de Curso de <b>"
						+ alunos + "</b> do" + curso + "<br>&emsp;" + cood;

				certificados.add(new CertificadoFields(data, mensagem));
			});

			// Exporta pdf
			byte[] pdf = service.generateCertificado(certificados);

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
