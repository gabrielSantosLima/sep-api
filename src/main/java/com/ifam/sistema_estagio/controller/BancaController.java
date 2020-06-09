package com.ifam.sistema_estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		ModelAndView modelAndView = new ModelAndView("");
		EstagioPCCT estagioPcct = estagioPcctService.findByEstagioOrProjetoId(id);

		if (estagioPcct == null) {
			modelAndView.addObject("mensagem", "Id informado não contém registro!");
		}

		List<Banca> bancas = service.findByEstagioPcct(estagioPcct);

		if (bancas.isEmpty()) {
			modelAndView.addObject("mensagem", "Não há bancas cadastradas!");
		} else {
			modelAndView.addObject("bancas", bancas);
		}

		return modelAndView;
	}

	// Create
	@PostMapping("/")
	@ResponseBody
	public ResponseEntity<Banca> create(@ModelAttribute("banca") Banca banca, @PathVariable Integer id) {
		EstagioPCCT estagioPcct = estagioPcctService.findByEstagioOrProjetoId(id);

		if (banca == null) {
			return ResponseEntity.badRequest().build();
		}

		banca.setEstagioPcct(estagioPcct);

		try {
			Banca bancaRegistrada = service.create(banca);

			return ResponseEntity.ok(bancaRegistrada);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Update
	@PutMapping("/{idBanca}")
	public ResponseEntity<Banca> update(@ModelAttribute("banca") Banca banca, @PathVariable("id") Integer id,
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
