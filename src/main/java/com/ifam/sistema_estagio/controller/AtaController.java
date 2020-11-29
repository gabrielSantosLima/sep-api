package com.ifam.sistema_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.services.AtaService;

@RestController
@RequestMapping("/atas")
@SuppressWarnings("unused")
public class AtaController {

	@Autowired
	private AtaService ataService;

}
