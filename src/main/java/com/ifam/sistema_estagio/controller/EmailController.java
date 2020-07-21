package com.ifam.sistema_estagio.controller;


import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifam.sistema_estagio.controller.service.EmailService;
import com.ifam.sistema_estagio.model.entity.EmailSimples;

@Controller
public class EmailController {
	
	@Autowired
	private EmailService service;
	
	@GetMapping("/resgatar-senha")
	public String resgatarSenha() {
		return "Email/index";
	}
	
	@PostMapping(path = "/resgatar-senha", consumes = "application/json")
	@ResponseBody
	public String resgatarSenha(@RequestBody EmailSimples email) {
		try {
			
			service.sendSimpleEmail(email);			
			System.out.println(email);

			return "E-mail enviado com sucesso! Verifique sua conta de email para atualizar seu cadastro!";
		}catch(EmailException e) {
			
			return "Erro no processo: " + e;
		}
	}

	@PostMapping(path = "/documentos", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String sendEmailAttachment(@RequestBody EmailSimples email, @RequestParam String pathFile) {
		try {
			service.sendEmailAttachment(email, pathFile);
			
			return "E-mail enviado com sucesso!";
		}catch(EmailException e) {
			
			return "Erro no processo! Tente Novamente!";
		}
	}
}
