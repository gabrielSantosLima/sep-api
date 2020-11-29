package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.EstagioPCCTDto;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.EstagioPcctService;

import java.util.List;

@RestController
@RequestMapping("/estagios")
@SuppressWarnings("unused")
public class EstagioPcctController {

	@Autowired
	private EstagioPcctService estagioPcctService;

	@GetMapping
	public ResponseEntity<List<EstagioPCCT>> listar(
			@RequestParam(required = false) Curso curso,
			@RequestParam(required = false) TipoServico tipoServico
	){
		try{
			List<EstagioPCCT> estagioPCCTS = estagioPcctService.listarPorCursoETipo(curso, tipoServico);
			return ResponseEntity.ok(estagioPCCTS);
		}catch (Exception error){
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<EstagioPCCT> salvar(@RequestBody EstagioPCCTDto estagioPCCTDto){
		try{
			EstagioPCCT estagioPcct = estagioPcctService.salvar(estagioPCCTDto.construirEntidade());
			return ResponseEntity.ok(estagioPcct);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
