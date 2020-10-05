package com.ifam.sistema_estagio.controller;

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

import com.ifam.sistema_estagio.controller.service.AlunoService;
import com.ifam.sistema_estagio.controller.service.EstagioPcctService;
import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@RestController
@RequestMapping("/estagio-pcct/{id}/aluno")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@Autowired
	private EstagioPcctService estagioService;

	private Optional<EstagioPCCT> getEstagioPcctById(Integer id) {
		return estagioService.findById(id);
	}

	// List
	@GetMapping(path = { "/", "" })
	public List<Aluno> list() {
		List<Aluno> alunos = service.list();

		return alunos;
	}

	// Create
	@PostMapping(path = { "/", "" })
	public ResponseEntity<Aluno> create(@RequestBody Aluno aluno, @PathVariable Integer id) {
		try {
			Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

			if (!estagioPcct.isPresent()) {
				return ResponseEntity.badRequest().build();
			}

			aluno.setEstagioPcct(estagioPcct.get());

			Aluno createdAluno = service.create(aluno);

			return ResponseEntity.ok(createdAluno);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{idAluno}")
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable("id") Integer id, @PathVariable("idAluno") Integer idAluno) {
		try {
			Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

			if (!estagioPcct.isPresent()) {
				return ResponseEntity.badRequest().build();
			}

			if (aluno == null) {
				return ResponseEntity.badRequest().build();
			}

			service.update(idAluno, aluno);

			return ResponseEntity.ok(aluno);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path = "/{idAluno}")
	public ResponseEntity<Aluno> delete(@PathVariable("id") Integer id ,@PathVariable("idAluno") Integer idAluno) {

		try {
			Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

			if (!estagioPcct.isPresent()) {
				return ResponseEntity.badRequest().build();
			}
			
			service.delete(idAluno);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// FindById
	@GetMapping(path = "/{idAluno}")
	public Aluno findById(@PathVariable("id") Integer id, @PathVariable("idAluno") Integer idAluno) {
		try {
			Optional<EstagioPCCT> estagioPcct = getEstagioPcctById(id);

			if (!estagioPcct.isPresent()) {
				return new Aluno();
			}
			
			Optional<Aluno> foundAluno = service.findById(idAluno);

			if (!foundAluno.isPresent()) {
				return new Aluno();
			}

			return foundAluno.get();
		} catch (Exception e) {

			return new Aluno();
		}
	}
}
