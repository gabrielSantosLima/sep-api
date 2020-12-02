package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.AtaService;

import java.util.HashMap;

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
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/banca/{idAta}")
	public ResponseEntity<Object> encontrarPorBanca(@PathVariable String idAta){
		try{
			val ata = ataService.encontrarPorBanca(idAta);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) throw new Exception("Ata não encontrada");
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/{idAta}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable String idAta){
		try{
			val ata = ataService.encontrarPorId(idAta);
			val ataNaoExiste = !ata.isPresent();
			if(ataNaoExiste) throw new Exception("Ata não encontrada");
			return ResponseEntity.ok(ata.get());
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@DeleteMapping("/{idAta}")
	public ResponseEntity<Object> deletar(@PathVariable String idAta){
		try{
			ataService.deletar(idAta);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}
}
