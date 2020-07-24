package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifam.sistema_estagio.controller.service.DocumentosService;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;

import net.sf.jasperreports.engine.JRException;


/*
 * Rota que será responsável por disponibilizar os documentos (
 * 	Ata, 
 * 	Certificado, 
 * 	Ficha de avaliação,
 * 	Memorando
 * )
 */

@Controller
@RequestMapping("/documentos")
public class DocumentosController {
	
	@Autowired
	private DocumentosService service;
	
	@GetMapping(path = "/certificado", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] getCertificado() {
		try {
			List<CertificadoFields> certificados = new ArrayList<>();
			certificados.add(new CertificadoFields("13 de Fevereiro de 2020", "Certificamos para os devidos fins de direito que o <b>Prof. MSc. Sérgio Augusto Coelho Bezerra</b> participou como orientador(a) e presidente na banca de defesa do Trabalho de Conclusão de Curso de <b>Adrielly Moraes Lustoza, Raquel Albuquerque Maciel do Curso Técnico Integrado em Informática.</b><br>\r\n" + 
					"Coordenação do Curso de Informática do Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM.\r\n" + 
					""));
			certificados.add(new CertificadoFields("13 de Fevereiro de 2020", "Certificamos para os devidos fins de direito que o <b>Prof. MSc. Sérgio Augusto Coelho Bezerra</b> participou como orientador(a) e presidente na banca de defesa do Trabalho de Conclusão de Curso de <b>Adrielly Moraes Lustoza, Raquel Albuquerque Maciel do Curso Técnico Integrado em Informática.</b><br>\r\n" + 
					"Coordenação do Curso de Informática do Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM.\r\n" + 
					""));
			certificados.add(new CertificadoFields("13 de Fevereiro de 2020", "Certificamos para os devidos fins de direito que o <b>Prof. MSc. Sérgio Augusto Coelho Bezerra</b> participou como orientador(a) e presidente na banca de defesa do Trabalho de Conclusão de Curso de <b>Adrielly Moraes Lustoza, Raquel Albuquerque Maciel do Curso Técnico Integrado em Informática.</b><br>\r\n" + 
					"Coordenação do Curso de Informática do Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM.\r\n" + 
					""));

			byte[] file = service.generateCertificado(certificados);
			
			return file;
		} catch (JRException e) {
			e.printStackTrace();

			return null;
		}
	}
}
