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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifam.sistema_estagio.controller.service.EstagioPcctService;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@Controller
@RequestMapping("/home/projeto")
public class ProjetoController {

	@Autowired
	private EstagioPcctService service;

	// List
	@GetMapping(path = { "/", "" })
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("Projeto/index");
		List<EstagioPCCT> projetos = service.getEstagiosOrProjeto(TipoServico.PROJETO);

		if (projetos.isEmpty() || projetos == null) {
			modelAndView.addObject("mensagem", "Não há estágios cadastrados");
		} else {
			modelAndView.addObject("projetos", projetos);
		}

		return modelAndView;
	}

	// Create
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> create(@RequestBody EstagioPCCT projeto) {

		try {
			EstagioPCCT createdProjeto = service.create(projeto);

			return ResponseEntity.ok(createdProjeto);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ResponseBody
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
	@DeleteMapping(path="/{id}", produces = "application/json")
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
	public ModelAndView findById(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("ProjetoDescricao/index");

		try {
			EstagioPCCT projeto = service.findById(id).get();

			if (projeto == null || projeto.getTipo() != TipoServico.PROJETO) {
				modelAndView.addObject("mensagem", "Código de estágio inválido!");

				throw new Exception("Código inválido!");
			}

			modelAndView.addObject("projeto", projeto);

			return modelAndView;
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());

			return modelAndView;
		}
	}
}
