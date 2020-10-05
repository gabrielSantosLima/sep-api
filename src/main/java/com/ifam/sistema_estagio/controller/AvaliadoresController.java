package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.controller.service.AvaliadoresService;
import com.ifam.sistema_estagio.model.entity.Avaliadores;

@RestController
@RequestMapping("/banca/{id}/avaliadores")
public class AvaliadoresController {

	@Autowired
	private AvaliadoresService service;

	@GetMapping(path = { "/", "" })
	public List<Avaliadores> list(@PathVariable Integer id) {
		try {
			
			 List<Avaliadores> avaliadores = service.findByBancaId(id);
			 
			 return avaliadores;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@PostMapping(path = {"/",""})
	public ResponseEntity<List<Avaliadores>> create(@RequestBody AvaliadoresJSONObject avaliadores, @PathVariable Integer id) {
		try {
			List<Avaliadores> createdAvaliadores = new ArrayList<>();

			avaliadores.getIdsProfessor().forEach(idProfessor -> {
				try {

					createdAvaliadores.add(service.createByIds(idProfessor, id));

				} catch (Exception e) {
				}
			});

			return ResponseEntity.ok(createdAvaliadores);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(path = "/{idAvaliador}")
	public ResponseEntity<Avaliadores> delete(@PathVariable("id") Integer idBanca,
			@PathVariable("idAvaliador") Integer idAvaliador) {
		try {
			service.delete(idAvaliador);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}

class AvaliadoresJSONObject{
	List<Integer> idsProfessor;
	
	public AvaliadoresJSONObject() {
		
	}
	
	public AvaliadoresJSONObject(List<Integer> idsProfessor) {
		this.idsProfessor = idsProfessor;
	}
	
	public List<Integer> getIdsProfessor(){
		return idsProfessor;
	}

	public void setIdsProfessor(List<Integer> idsProfessor){
		this.idsProfessor = idsProfessor;
	}
}