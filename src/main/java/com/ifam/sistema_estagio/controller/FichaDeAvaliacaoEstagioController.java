package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.FichaAvaliacaoEstagioDto;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.FichaDeAvaliacaoEstagioService;

@RestController
@RequestMapping("/fichas-de-avaliacao-estagio")
@SuppressWarnings("unused")
public class FichaDeAvaliacaoEstagioController {
	
	@Autowired
	private FichaDeAvaliacaoEstagioService fichaDeAvaliacaoEstagioService;

	@PostMapping
	public ResponseEntity<FichaDeAvaliacaoEstagio> cadastrarFicha(
			@RequestBody FichaAvaliacaoEstagioDto fichaAvaliacaoEstagioDto
	){
		try{
			val fichaCriada =  fichaDeAvaliacaoEstagioService.salvar(fichaAvaliacaoEstagioDto.construirEntidade());
			return ResponseEntity.ok(fichaCriada);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

}