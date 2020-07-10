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

import com.ifam.sistema_estagio.controller.service.BancaService;
import com.ifam.sistema_estagio.controller.service.EstagioPcctService;
import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@Controller
@RequestMapping(value= {"/estagio/{id}/bancas","/projeto/{id}/bancas"})
public class BancaController {

	@Autowired
	private BancaService service;

	@Autowired
	private EstagioPcctService estagioPcctService;

	private Optional<EstagioPCCT> getEstagioPcctById(Integer id){
		return estagioPcctService.findById(id);
	}
	
	// List
	@GetMapping(path = {"/", ""}, produces = "application/json")
	@ResponseBody
	public List<Banca> list(@PathVariable Integer id){
		Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);
		
		if(!estagioPcct.isPresent()) {
			return new ArrayList<>();
		}
		
		List<Banca> bancas = service.findByEstagioPcct(estagioPcct.get());
		
		return bancas;
	}

	// Create
	@PostMapping(path = {"/",""}, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Banca> create(@RequestBody Banca banca, @PathVariable Integer id) {
		Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

		if (!estagioPcct.isPresent()) {
			return ResponseEntity.badRequest().body(banca);
		}
		
		if (banca == null) {
			return ResponseEntity.badRequest().body(banca);
		}
		
		try {
			banca.setEstagioPcct(estagioPcct.get());
			
			Banca createdBanca = service.create(banca);

			return ResponseEntity.ok(createdBanca);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{idBanca}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Banca> update(@RequestBody Banca banca, @PathVariable("id") Integer id,
			@PathVariable("idBanca") Integer idBanca) {
		Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

		if (banca == null || !banca.getEstagioPcct().equals(estagioPcct.get())) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			Banca bancaAtualizada = service.update(idBanca, banca);

			return ResponseEntity.ok(bancaAtualizada);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path = "/{idBanca}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Banca> delete(@PathVariable("id") Integer id, 
			@PathVariable("idBanca") Integer idBanca) {
		Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

		try {
			if(id != estagioPcct.get().getId()) {
				return ResponseEntity.badRequest().build();
			}
			
			service.delete(idBanca);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}
}
