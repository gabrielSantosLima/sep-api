package com.ifam.sistema_estagio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.services.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService service;

}
