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
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.services.AtaService;
import com.ifam.sistema_estagio.services.FichaDeAvaliacaoProjetoService;

@Controller
@RequestMapping(value = "atas/{id}/avaliacao-projeto")
public class FichaDeAvaliacaoProjetoController {

	@Autowired
	private FichaDeAvaliacaoProjetoService service;

	@Autowired
	private AtaService ataService;

	private Ata getAtaById(Integer id) {
		return ataService.findById(id).get();
	}

	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<FichaDeAvaliacaoProjeto>> list(@PathVariable Integer id) {
		Ata ata = getAtaById(id);
		List<FichaDeAvaliacaoProjeto> fichas = service.findByAta(ata);

		if (fichas.isEmpty()) {
			return ResponseEntity.badRequest().body(fichas);
		}

		return ResponseEntity.ok(fichas);
	}

	@PostMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoProjeto> create(@RequestBody FichaDeAvaliacaoProjeto ficha,
			@PathVariable Integer id) {
		try {
			Ata ata = getAtaById(id);

			ficha.setAta(ata);

			FichaDeAvaliacaoProjeto createdFicha = service.create(ficha);

			return ResponseEntity.ok(createdFicha);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(value = { "/{idFicha}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoProjeto> create(@RequestBody FichaDeAvaliacaoProjeto ficha,
			@PathVariable("id") Integer id, @PathVariable("idFicha") Integer idFicha) {
		try {
			Ata ata = getAtaById(id);
	
			if (!ficha.getAta().equals(ata)) {
				return ResponseEntity.badRequest().build();
			}

			FichaDeAvaliacaoProjeto updatedFicha = service.update(idFicha, ficha);

			return ResponseEntity.ok(updatedFicha);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = { "/{idFicha}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<FichaDeAvaliacaoProjeto> create(@PathVariable("id") Integer id,
			@PathVariable("idFicha") Integer idFicha) {
		try {
			Ata ata = getAtaById(id);
			FichaDeAvaliacaoProjeto ficha = service.findById(idFicha).get();
	
			if (!ficha.getAta().equals(ata)) {
				return ResponseEntity.badRequest().build();
			}

			service.delete(idFicha);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}