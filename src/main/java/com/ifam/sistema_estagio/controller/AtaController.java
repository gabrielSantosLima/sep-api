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

import com.ifam.sistema_estagio.controller.service.AtaService;
import com.ifam.sistema_estagio.controller.service.BancaService;
import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.Banca;

@Controller
@RequestMapping("bancas/{id}/atas")
public class AtaController {

	@Autowired
	private AtaService service;

	@Autowired
	private BancaService bancaService;
	
	private Banca getBancaById(Integer id) {
		return bancaService.findById(id).get();
	}
	
	// List all
	@GetMapping("/")
	public ModelAndView list(@PathVariable Integer id){
		ModelAndView modelAndView = new ModelAndView("");
		Banca banca = getBancaById(id);
		
		List<Ata> atas = service.findByBanca(banca);
				
		if(atas.isEmpty()) {
			modelAndView.addObject("message", "Não há atas cadastradas");
		}else {
			modelAndView.addObject("atas", atas);			
		}
		
		return modelAndView;
	}
	
	// Create
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Ata> create(@PathVariable Integer id, @RequestBody Ata ata){
		Banca banca = getBancaById(id);
		ata.setBanca(banca);
		
		try {
			Ata createdAta;
			
			createdAta = service.create(ata);
			
			return ResponseEntity.ok(createdAta);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	// Update

	@PutMapping(path = "/{idAta}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Ata> update(@RequestBody Ata ata, @PathVariable("id") Integer id,
			@PathVariable("idAta") Integer idAta) {

		if (ata == null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			Ata updatedAta = service.update(idAta, ata);

			return ResponseEntity.ok(updatedAta);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}
	
	// Delete
	@DeleteMapping("/{idAta}")
	@ResponseBody
	public ResponseEntity<Ata> delete(@PathVariable("id") Integer id, 
			@PathVariable("idAta") Integer idAta) {

		try {
			service.delete(idAta);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}
	
	// Find by Banca
	@GetMapping("/{idAta}")
	@ResponseBody
	public ResponseEntity<Ata> findById(@PathVariable("id") Integer id, 
			@PathVariable("idAta") Integer idAta) {
		Banca banca = getBancaById(id);
		
		try {
			Ata ata = service.findById(idAta).get();
			
			if(banca.equals(ata.getBanca())) {
				return ResponseEntity.badRequest().build();				
			}
			
			return ResponseEntity.ok(ata);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
