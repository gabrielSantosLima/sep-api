package com.ifam.sistema_estagio.controller;

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

import com.ifam.sistema_estagio.controller.service.AlunoService;
import com.ifam.sistema_estagio.model.entity.Aluno;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	@Autowired
	private AlunoService service;

	// List
	@GetMapping(path = {"/", ""}, produces = "application/json")
	@ResponseBody
	public List<Aluno> list(){
		List<Aluno> alunos = service.list();
		
		return alunos;
	}

	// Create
	@PostMapping(path = {"/", ""}, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
		
		try {
			Aluno createdAluno = service.create(aluno);

			return ResponseEntity.ok(createdAluno);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Integer id) {

		if (aluno == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			service.update(id, aluno);

			return ResponseEntity.ok(aluno);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path= "/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Aluno> delete(@PathVariable Integer id) {

		try {
			service.delete(id);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// FindById
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public Aluno findById(@PathVariable("id") Integer id) {
		try {
			Optional<Aluno> foundAluno = service.findById(id);

			if(!foundAluno.isPresent()) {
				return new Aluno();
			}
			
			return foundAluno.get();
		} catch (Exception e) {
			
			return new Aluno();
		}
	}
}
