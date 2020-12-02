package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Ata;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.AtaService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/atas")
@SuppressWarnings("unused")
public class AtaController {

	@Autowired
	private AtaService ataService;

	@GetMapping
	public ResponseEntity<Object> listar(){
		try{
			val atas = ataService.listar();
			return ResponseEntity.ok(atas);
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
			mensagem.put("mensagem", e.getMessage());
			mensagem.put("status", 404);
			return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/banca/{idBanca}")
	public ResponseEntity<Object> encontrarPorBanca(@PathVariable String idBanca){
		try{
			val ata = ataService.encontrarPorBanca(idBanca);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) return ResponseEntity.ok().build();
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
			mensagem.put("mensagem", e.getMessage());
			mensagem.put("status", 404);
			return ResponseEntity.status(404).body(mensagem);
		}
	}

	@GetMapping("/{idBanca}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable String idAta){
		try{
			val ata = ataService.encontrarPorId(idAta);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) return ResponseEntity.ok().build();
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
			mensagem.put("mensagem", e.getMessage());
			mensagem.put("status", 404);
			return ResponseEntity.status(404).body(mensagem);
		}
	}

	@DeleteMapping("/{idAta}")
	public ResponseEntity<Object> deletar(@PathVariable String idAta){
		try{
			ataService.deletar(idAta);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			val mensagem = new HashMap<String, Object>();
			mensagem.put("mensagem", e.getMessage());
			mensagem.put("status", 404);
			return ResponseEntity.status(404).body(mensagem);
		}
	}
}
