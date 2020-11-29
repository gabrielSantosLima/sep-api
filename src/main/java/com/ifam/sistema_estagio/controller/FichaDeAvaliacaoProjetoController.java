package com.ifam.sistema_estagio.controller;

import java.util.List;

import com.ifam.sistema_estagio.dto.FichaAvaliacaoEstagioDto;
import com.ifam.sistema_estagio.dto.FichaAvaliacaoProjetoDto;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.services.AtaService;
import com.ifam.sistema_estagio.services.FichaDeAvaliacaoProjetoService;

@RestController
@RequestMapping("/fichas-de-avaliacao-projeto")
@SuppressWarnings("unused")
public class FichaDeAvaliacaoProjetoController {

	@Autowired
	private FichaDeAvaliacaoProjetoService fichaDeAvaliacaoProjetoService;

	@PostMapping
	public ResponseEntity<FichaDeAvaliacaoProjeto> cadastrarFicha(
			@RequestBody FichaAvaliacaoProjetoDto fichaAvaliacaoProjetoDto
	){
		try{
			val fichaCriada =  fichaDeAvaliacaoProjetoService.salvar(fichaAvaliacaoProjetoDto.construirEntidade());
			return ResponseEntity.ok(fichaCriada);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}