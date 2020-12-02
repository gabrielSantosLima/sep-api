package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.FichaAvaliacaoEstagioDto;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.FichaDeAvaliacaoEstagioService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/fichas-de-avaliacao-estagio")
@SuppressWarnings("unused")
public class FichaDeAvaliacaoEstagioController {
	
	@Autowired
	private FichaDeAvaliacaoEstagioService fichaDeAvaliacaoEstagioService;

	@PostMapping
	public ResponseEntity<Object> salvar(
			@RequestBody FichaAvaliacaoEstagioDto fichaAvaliacaoEstagioDto
	){
		try{
			val fichaCriada =  fichaDeAvaliacaoEstagioService.salvar(fichaAvaliacaoEstagioDto.construirEntidade());
			return ResponseEntity.ok(fichaCriada);
		}catch(Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping
	public ResponseEntity<Object> listar(){
		try{
			val fichas = fichaDeAvaliacaoEstagioService.listar();
			return ResponseEntity.ok(fichas);
		}catch(Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/{idFicha}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable String idFicha){
		try{
			val ficha = fichaDeAvaliacaoEstagioService.encontrarPorId(idFicha);
			val fichaNaoExiste = !ficha.isPresent();
			if(fichaNaoExiste) return null;
			return ResponseEntity.ok(ficha.get());
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@PutMapping
	public ResponseEntity<Object> atualizar(
			@RequestBody FichaAvaliacaoEstagioDto fichaAvaliacaoEstagioDto
	){
		try{
			val fichaAtualizada =  fichaDeAvaliacaoEstagioService.atualizar(
					fichaAvaliacaoEstagioDto.getId(),
					fichaAvaliacaoEstagioDto.construirEntidade()
			);
			return ResponseEntity.ok(fichaAtualizada);
		}catch(Exception e){
			val mensagem = new HashMap<String, Object>();
            mensagem.put("mensagem", e.getMessage());
            mensagem.put("status", 404);
            return ResponseEntity.status(404).body(mensagem);
		}
	}

	@DeleteMapping("/{idFicha}")
	public ResponseEntity<Object> deletar(@PathVariable String idFicha){
		try{
			fichaDeAvaliacaoEstagioService.deletar(idFicha);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
			mensagem.put("mensagem", e.getMessage());
			mensagem.put("status", 404);
			return ResponseEntity.status(404).body(mensagem);
		}
	}
}