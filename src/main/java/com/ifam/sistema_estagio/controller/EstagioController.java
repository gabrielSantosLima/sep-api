package com.ifam.sistema_estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.ifam.sistema_estagio.controller.service.EstagioService;
import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Estagio;

@Controller
@RequestMapping("/home/estagio")
public class EstagioController {

	@Autowired
	private EstagioService service;

	// List
	@GetMapping("/")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("Estagio/index");
		List<Estagio> estagios = service.listEstagios();

		if (estagios.isEmpty() || estagios == null) {
			modelAndView.addObject("mensagem", "Não há estágios cadastrados");
		} else {
			modelAndView.addObject("estagios", estagios);
		}

		return modelAndView;
	}

	// Create
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<EstagioPCCT> create(@RequestBody EstagioPCCT estagio, List<Aluno> alunos) {
		try {
			service.saveAsEstagio(estagio, alunos);

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
			EstagioPCCT EstagioPCCTAtualizado = service.updateEstagio(id, estagioPCCT);

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
	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("EstagioDescricao/index");

		try {
			Estagio estagio = (Estagio) service.findById(id).get();

			if (estagio == null) {
				modelAndView.addObject("mensagem","Código de estágio inválido!");
				
				throw new Exception("Código inválido!");
			}

			modelAndView.addObject("estagio", estagio);
			
			return modelAndView;
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			
			return modelAndView;
		}
	}
}
