package com.ifam.sistema_estagio.services;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.repository.AtaRepository;

import java.util.Optional;

@Service
public class AtaService extends GenericService<Ata, AtaRepository>{

	@Autowired
	private AtaRepository ataRepository;

	@Autowired
	private BancaService bancaService;

	public Optional<Ata> encontrarPorBanca(String idBanca) throws Exception {
		if(idBanca.isEmpty()) throw new Exception("Id de banca inválido");
		val banca = bancaService.encontrarPorId(idBanca);
		val bancaNaoExiste = !banca.isPresent();
		if(bancaNaoExiste) throw new Exception("Banca não existe");
		return ataRepository.findByBanca(banca.get());
	}
}
