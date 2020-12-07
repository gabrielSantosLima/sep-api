package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.dto.EstagioPCCTDto;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.services.EstagioPcctService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estagios-pcct")
@SuppressWarnings("unused")
public class EstagioPcctController {

	@Autowired
	private EstagioPcctService estagioPcctService;

	@GetMapping
	public ResponseEntity<Object> listar(
			@RequestParam(required = false) TipoServico tipoServico
	){
		try{
			val estagioPCCTS = estagioPcctService.listar(tipoServico);
			return ResponseEntity.ok(estagioPCCTS);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody EstagioPCCTDto estagioPCCTDto){
		try{
			val estagioPcctCriado = estagioPcctService.salvar(estagioPCCTDto.construirEntidade());
			return ResponseEntity.ok(estagioPcctCriado);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/{idEstagio}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable String idEstagio){
		try{
			val estagioPcct = estagioPcctService.encontrarPorId(idEstagio);
			val estagioPcctNaoExiste = !estagioPcct.isPresent();
			if(estagioPcctNaoExiste) throw new Exception("Estágio/PCCT não encontrado");;
			return ResponseEntity.ok(estagioPcct.get());
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PutMapping
	public ResponseEntity<Object> atualizar(@RequestBody EstagioPCCTDto estagioPCCTDto){
		try{
			val estagioPcctAtualizado = estagioPcctService.atualizar(
					estagioPCCTDto.getId(),
					estagioPCCTDto.construirEntidade()
			);
			return ResponseEntity.ok(estagioPcctAtualizado);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@DeleteMapping("/{idEstagioPcct}")
	public ResponseEntity<Object> deletar(@PathVariable String idEstagioPcct){
		try{
			estagioPcctService.deletar(idEstagioPcct);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@PostMapping("/concluir/{idEstagioPcct}")
	public ResponseEntity<Object> finalizarEstagioPcct(@PathVariable String idEstagioPcct){
		try{
			val estagioPcct = estagioPcctService.finalizarEstagioPcct(idEstagioPcct);
			return ResponseEntity.ok(estagioPcct);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}
}
