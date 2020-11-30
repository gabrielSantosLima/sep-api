package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.FichaAvaliacaoProjetoDto;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.FichaDeAvaliacaoProjetoService;

import java.util.List;

@RestController
@RequestMapping("/fichas-de-avaliacao-projeto")
@SuppressWarnings("unused")
public class FichaDeAvaliacaoProjetoController {

	@Autowired
	private FichaDeAvaliacaoProjetoService fichaDeAvaliacaoProjetoService;

	@PostMapping
	public ResponseEntity<FichaDeAvaliacaoProjeto> salvar(
			@RequestBody FichaAvaliacaoProjetoDto fichaAvaliacaoProjetoDto
	){
		try{
			val fichaCriada =  fichaDeAvaliacaoProjetoService.salvar(fichaAvaliacaoProjetoDto.construirEntidade());
			return ResponseEntity.ok(fichaCriada);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<FichaDeAvaliacaoProjeto>> listar(){
		try{
			val fichas = fichaDeAvaliacaoProjetoService.listar();
			return ResponseEntity.ok(fichas);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{idFicha}")
	public ResponseEntity<FichaDeAvaliacaoProjeto> encontrarPorId(@PathVariable String idFicha){
		try{
			val ficha = fichaDeAvaliacaoProjetoService.encontrarPorId(idFicha);
			val fichaNaoExiste = !ficha.isPresent();
			if(fichaNaoExiste) return null;
			return ResponseEntity.ok(ficha.get());
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<FichaDeAvaliacaoProjeto> atualizar(
			@RequestBody FichaAvaliacaoProjetoDto fichaAvaliacaoProjetoDto
	){
		try{
			val fichaAtualizada =  fichaDeAvaliacaoProjetoService.atualizar(
					fichaAvaliacaoProjetoDto.getId(),
					fichaAvaliacaoProjetoDto.construirEntidade()
			);
			return ResponseEntity.ok(fichaAtualizada);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{idFicha}")
	public ResponseEntity<Boolean> deletar(@PathVariable String idFicha){
		try{
			fichaDeAvaliacaoProjetoService.deletar(idFicha);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(false);
		}
	}
}