package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.entity.Ata;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.AtaService;

import java.util.List;

@RestController
@RequestMapping("/atas")
@SuppressWarnings("unused")
public class AtaController {

	@Autowired
	private AtaService ataService;

	@GetMapping
	public ResponseEntity<List<Ata>> listar(){
		try{
			val atas = ataService.listar();
			return ResponseEntity.ok(atas);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/banca/{idBanca}")
	public ResponseEntity<Ata> encontrarPorBanca(@PathVariable String idBanca){
		try{
			val ata = ataService.encontrarPorBanca(idBanca);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) return ResponseEntity.ok().build();
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{idBanca}")
	public ResponseEntity<Ata> encontrarPorId(@PathVariable String idAta){
		try{
			val ata = ataService.encontrarPorId(idAta);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) return ResponseEntity.ok().build();
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{idAta}")
	public ResponseEntity<Boolean> deletar(@PathVariable String idAta){
		try{
			ataService.deletar(idAta);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(false);
		}
	}
}
