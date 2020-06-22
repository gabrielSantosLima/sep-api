package com.ifam.sistema_estagio.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifam.sistema_estagio.controller.service.BancaService;
import com.ifam.sistema_estagio.controller.service.EstagioPcctService;
import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@Controller
@RequestMapping("/estagio-pcct/{id}/bancas")
public class BancaController {

	@Autowired
	private BancaService service;

	@Autowired
	private EstagioPcctService estagioPcctService;

	// List
	@GetMapping("/")
	public ModelAndView list(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("index");
		Optional<EstagioPCCT> estagioPcct = estagioPcctService.findById(id);

		if (!estagioPcct.isPresent()) {
			modelAndView.addObject("mensagem", "Id informado não contém registro!");
			return modelAndView;
		}

		List<Banca> bancas = service.findByEstagioPcct(estagioPcct.get());

		if (bancas.isEmpty()) {
			modelAndView.addObject("mensagem", "Não há bancas cadastradas!");
		} else {
			modelAndView.addObject("bancas", bancas);
		}

		return modelAndView;
	}

	// Create
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Banca> create(@RequestBody Banca banca, @PathVariable Integer id) {
		Optional<EstagioPCCT> estagioPcct = estagioPcctService.findById(id);

		if (!estagioPcct.isPresent()) {
			return ResponseEntity.badRequest().body(banca);
		}
		
		if (banca == null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			Banca bancaIdentificada = service.setEstagioOrProjeto(banca, estagioPcct.get());
			Banca bancaRegistrada = service.create(bancaIdentificada);

			return ResponseEntity.ok(bancaRegistrada);
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

		if (banca == null) {
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
	@DeleteMapping("/{idBanca}")
	@ResponseBody
	public ResponseEntity<Banca> delete(@PathVariable("id") Integer id, 
			@PathVariable("idBanca") Integer idBanca) {

		try {
			service.delete(idBanca);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}
}
