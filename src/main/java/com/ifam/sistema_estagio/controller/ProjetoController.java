package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.services.EstagioPcctService;
import com.ifam.sistema_estagio.services.ProfessorService;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	private EstagioPcctService service;
	
	@Autowired
	private ProfessorService professorService;
	
	// List
	@GetMapping(path = { "/", "" })
	public List<EstagioPCCT> list() {
		List<EstagioPCCT> projetos = service.getEstagiosOrProjeto(TipoServico.PROJETO);

		if (projetos.isEmpty() || projetos == null) {
			return new ArrayList<>();
		}
		
		return projetos;
	}

	// Create
	@PostMapping(path = "/")
	public ResponseEntity<EstagioPCCT> create(@RequestBody EstagioPCCT projeto) {

		try {
			Optional<Professor> professor = professorService.findById(projeto.getResponsavel().getId());
			
			if(!professor.isPresent()) {
				return ResponseEntity.badRequest().build();				
			}
			
			projeto.setResponsavel(professor.get());
			
			EstagioPCCT createdProjeto = service.create(projeto);

			return ResponseEntity.ok(createdProjeto);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{id}")
	public ResponseEntity<EstagioPCCT> update(@RequestBody EstagioPCCT projeto, @PathVariable("id") Integer id) {
		try {
			if (projeto == null) {
				return ResponseEntity.badRequest().build();
			}

			service.update(id, projeto);

			return ResponseEntity.ok(projeto);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path="/{id}")
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
			Optional<EstagioPCCT> projeto = service.findById(id);
			
			if(!projeto.isPresent()) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(projeto.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
