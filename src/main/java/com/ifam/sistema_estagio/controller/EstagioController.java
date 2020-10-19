package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.services.EstagioPcctService;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@Controller
@RequestMapping("/estagio")
public class EstagioController {

	@Autowired
	private EstagioPcctService service;

	// List
	@GetMapping(path = { "/", "" })
	public List<EstagioPCCT> list() {
		List<EstagioPCCT> estagios = service.getEstagiosOrProjeto(TipoServico.ESTAGIO);

		if (estagios.isEmpty() || estagios == null) {
			return new ArrayList<>();
		}
		
		return estagios;
	}

	// Create
	@PostMapping(path = { "/", "" }, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> create(@RequestBody EstagioPCCT estagio) {

		try {
			EstagioPCCT createdEstagio = service.create(estagio);

			return ResponseEntity.ok(createdEstagio);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> update(@RequestBody EstagioPCCT estagio, @PathVariable("id") Integer id) {
		try {

			if (estagio == null) {
				return ResponseEntity.badRequest().build();
			}

			service.update(id, estagio);

			return ResponseEntity.ok(estagio);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path = { "/{id}", "" }, produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> delete(@PathVariable("id") Integer id) {

		try {
			service.delete(id);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// FindById
	@GetMapping("/{id}")
	public ResponseEntity<EstagioPCCT> findById(@PathVariable("id") Integer id) {
		try {
			Optional<EstagioPCCT> estagio = service.findById(id);
			
			if(!estagio.isPresent()) {
				return ResponseEntity.badRequest().build();
			}
			
			
			return ResponseEntity.ok(estagio.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
