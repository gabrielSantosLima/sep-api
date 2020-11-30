package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.EstagioPCCTDto;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.EstagioPcctService;

import java.util.List;

@RestController
@RequestMapping("/estagios-pcct")
@SuppressWarnings("unused")
public class EstagioPcctController {

	@Autowired
	private EstagioPcctService estagioPcctService;

	@GetMapping
	public ResponseEntity<List<EstagioPCCT>> listar(
			@RequestParam(required = false) TipoServico tipoServico
	){
		try{
			val estagioPCCTS = estagioPcctService.listar(tipoServico);
			return ResponseEntity.ok(estagioPCCTS);
		}catch (Exception error){
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<EstagioPCCT> salvar(@RequestBody EstagioPCCTDto estagioPCCTDto){
		try{
			val estagioPcctCriado = estagioPcctService.salvar(estagioPCCTDto.construirEntidade());
			return ResponseEntity.ok(estagioPcctCriado);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{idEstagioPcct}")
	public ResponseEntity<EstagioPCCT> encontrarPorId(@PathVariable String idEstagioPcct){
		try{
			val estagioPcct = estagioPcctService.encontrarPorId(idEstagioPcct);
			val estagioPcctNaoExiste = !estagioPcct.isPresent();
			if(estagioPcctNaoExiste) return ResponseEntity.ok().build();
			return ResponseEntity.ok(estagioPcct.get());
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<EstagioPCCT> atualizar(@RequestBody EstagioPCCTDto estagioPCCTDto){
		try{
			val estagioPcctAtualizado = estagioPcctService.atualizar(
					estagioPCCTDto.getId(),
					estagioPCCTDto.construirEntidade()
			);
			return ResponseEntity.ok(estagioPcctAtualizado);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{idEstagioPcct}")
	public ResponseEntity<Boolean> deletar(@PathVariable String idEstagioPcct){
		try{
			estagioPcctService.deletar(idEstagioPcct);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(false);
		}
	}

	@PostMapping("/concluir/{idEstagioPcct}")
	public ResponseEntity<EstagioPCCT> concluirEstagioPcct(@PathVariable String idEstagioPcct){
		try{
			val estagioPcct = estagioPcctService.concluirEstagio(idEstagioPcct);
			return ResponseEntity.ok(estagioPcct);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
