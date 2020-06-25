package com.ifam.sistema_estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifam.sistema_estagio.controller.service.EstagioService;
import com.ifam.sistema_estagio.controller.service.ProjetoService;
import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.Estagio;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Projeto;

public class ProjetoController {

	@Autowired
	private ProjetoService service;

	// List
	@GetMapping("/")
	public ModelAndView list(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("");
		List<Projeto> projetos = service.listProjetos();

		if (projetos.isEmpty() || projetos == null) {
			modelAndView.addObject("mensagem", "Não há estágios cadastrados");
		} else {
			modelAndView.addObject("estagios", projetos);
		}

		return modelAndView;
	}

	// Create
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> create(@RequestBody EstagioPCCT projeto, List<Aluno> alunos) {
		try {
			service.saveAsProjeto(projeto, alunos);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Update
	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> update(@RequestBody EstagioPCCT estagioPCCT, @PathVariable("id") Integer id) {

		if (estagioPCCT == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			EstagioPCCT EstagioPCCTAtualizado = service.updateProjeto(id, estagioPCCT);

			return ResponseEntity.ok(EstagioPCCTAtualizado);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping("/{id}")
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
	@GetMapping(path = "/{id}",consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> findById(@PathVariable("id") Integer id) {
		Projeto projeto;

		try {
			projeto = (Projeto) service.findById(id).get();

			if (projeto == null) {
				return ResponseEntity.badRequest().build();
			}

			return ResponseEntity.ok(projeto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
