package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.exceptions.ErroRequisicaoFactoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;

@RestController
@RequestMapping("/solicitar-banca")
@SuppressWarnings("unused")
public class SolicitarBancaController {
	
	@Autowired
	private SolicitarBancaProcess solicitarBancaProcess;
	
	@PostMapping
	public ResponseEntity<Object> iniciar(@RequestBody BancaDto banca) {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.iniciarProcesso(banca));
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listarQuantidadeDeProcessos() {
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarQuantidadeDeProcessos());
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/retornar-banca/{idProcesso}")
	public ResponseEntity<Object> listarBancaPorProcesso(@PathVariable String idProcesso){
		try{
			return ResponseEntity.ok(solicitarBancaProcess.listarBancaPorProcesso(idProcesso));
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/{idParticipante}")
	public ResponseEntity<Object> confirmarParticipacao(
			@PathVariable String idProcesso,
			@PathVariable String idParticipante,
			@RequestParam Boolean resposta
	){
		try{
			solicitarBancaProcess.confirmarParticipacao(idProcesso, idParticipante, resposta);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/confirmar-participacao/{idProcesso}/todos")
	public ResponseEntity<Object> confirmarParticipacaoTodos(@PathVariable String idProcesso){
		try{
			solicitarBancaProcess.confirmarParticipacaoTodos(idProcesso);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}

	@GetMapping("/aprovar-banca/{idProcesso}")
	public ResponseEntity<Object> aprovarBanca(
			@PathVariable String idProcesso,
			@RequestParam Boolean resposta
	){
		try{
			solicitarBancaProcess.verificarAprovacaoBanca(idProcesso, resposta);
			return ResponseEntity.ok(true);
		}catch (Exception e){
			return ErroRequisicaoFactoryException.construir(e);
		}
	}
}
