package com.ifam.sistema_estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.services.AtaService;
import com.ifam.sistema_estagio.services.FichaDeAvaliacaoEstagioService;

@Controller
@RequestMapping(value = "ata/{id}/avaliacao-estagio")
public class FichaDeAvaliacaoEstagioController {
	
	@Autowired
	private FichaDeAvaliacaoEstagioService service;

	@Autowired
	private AtaService ataService;
	
	private Ata getAtaById(Integer id) {
		return ataService.encontrarPorId(id).get();
	}
	
	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<FichaDeAvaliacaoEstagio>> list(@PathVariable Integer id) {
		Ata ata = getAtaById(id);
		List<FichaDeAvaliacaoEstagio> fichas = service.findByAta(ata);
		
		if(fichas.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(fichas);
	}
	
	@PostMapping(value = {"", "/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoEstagio> create(@RequestBody FichaDeAvaliacaoEstagio ficha, @PathVariable Integer id) {
		try{
			Ata ata = getAtaById(id);
		
			ficha.setAta(ata);
			
			FichaDeAvaliacaoEstagio createdFicha = service.salvar(ficha);
			
			return ResponseEntity.ok(createdFicha);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(value = {"/{idFicha}"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoEstagio> create(@RequestBody FichaDeAvaliacaoEstagio ficha, 
			@PathVariable("id") Integer id, 
			@PathVariable("idFicha") Integer idFicha) {
		try{
			Ata ata = getAtaById(id);
		
			if(!ficha.getAta().equals(ata)) {
				return ResponseEntity.badRequest().build();
			}
		
			FichaDeAvaliacaoEstagio updatedFicha = service.atualizar(idFicha, ficha);
			
			return ResponseEntity.ok(updatedFicha);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = {"/{idFicha}"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoEstagio> create(@PathVariable("id") Integer id, 
			@PathVariable("idFicha") Integer idFicha) {
		try{
		
			Ata ata = getAtaById(id);
			FichaDeAvaliacaoEstagio ficha = service.encontrarPorId(idFicha).get();
		
			if(!ficha.getAta().equals(ata)) {
				return ResponseEntity.badRequest().build();
			}
		
			service.deletar(idFicha);
			
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}