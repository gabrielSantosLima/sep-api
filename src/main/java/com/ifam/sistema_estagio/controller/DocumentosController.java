package com.ifam.sistema_estagio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifam.sistema_estagio.controller.service.DocumentosService;
import com.ifam.sistema_estagio.model.entity.Certificado;

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
			List<Certificado> certificados = new ArrayList<>();
			certificados.add(new Certificado("13 de Fevereiro de 2020", "Certificamos para os devidos fins de direito que <b>RONALDO COSTA DE FREITAS</b>\r\n" + 
					"	 atuou como <b>Desenvolvedor Back-End</b> no Projeto de Extensão - Fábrica de\r\n" + 
					"	 Software do CMC, promovido pelo Instituto Federal de Educação, Ciência e\r\n" + 
					"	 Tecnologia."));
			certificados.add(new Certificado("13 de Fevereiro de 2020", "Boraaa"));
			certificados.add(new Certificado("13 de Fevereiro de 2020", "Boraaa"));

			byte[] file = service.generateCertificado(certificados);
			
			return file;
		} catch (JRException | IOException e) {
			e.printStackTrace();

			return null;
		}
	}
}
