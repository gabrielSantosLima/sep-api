package com.ifam.sistema_estagio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.services.AtaService;
import com.ifam.sistema_estagio.services.BancaService;

@RestController
@RequestMapping("/bancas/{id}/atas")
public class AtaController {

	@Autowired
	private AtaService service;

	@Autowired
	private BancaService bancaService;

	private Optional<Banca> getBancaById(Integer id) {
		return bancaService.encontrarPorId(id);
	}

	// List all
	@GetMapping(path = { "/", "" })
	public List<Ata> list(@PathVariable Integer id) {
		Optional<Banca> banca = getBancaById(id);

		if (!banca.isPresent()) {
			return new ArrayList<>();
		}

		List<Ata> atas = service.findByBanca(banca.get());

		return atas;
	}

	// Create
	@PostMapping(path = { "/", "" })
	public ResponseEntity<Ata> create(@PathVariable Integer id, @RequestBody Ata ata) {
		try {
			Optional<Banca> banca = getBancaById(id);

			if (!banca.isPresent()) {
				return ResponseEntity.badRequest().build();
			}

			Ata createdAta = service.create(ata, banca.get());

			return ResponseEntity.ok(createdAta);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	// Update
	@PutMapping(path = "/{idAta}")
	public ResponseEntity<Ata> update(@RequestBody Ata ata,
			@PathVariable("id") Integer id, @PathVariable("idAta") Integer idAta) {
		try {

			if (ata == null) {
				return ResponseEntity.badRequest().build();
			}

			Ata updatedAta = service.atualizar(idAta, ata);

			return ResponseEntity.ok(updatedAta);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Delete
	@DeleteMapping(path = "/{idAta}")
	public ResponseEntity<Ata> delete(@PathVariable("id") Integer id,
			@PathVariable("idAta") Integer idAta) {

		try {
			service.deletar(idAta);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	// Find by Banca
	@GetMapping(path = "/{idAta}")
	public Ata findById(@PathVariable("id") Integer id,
			@PathVariable("idAta") Integer idAta) {
		try {
			Optional<Banca> banca = getBancaById(id);

			if (!banca.isPresent()) {
				return new Ata();
			}

			Ata ata = service.encontrarPorId(idAta).get();

			if (id == ata.getBanca().getId()) {
				return new Ata();
			}

			return ata;
		} catch (Exception e) {
			return new Ata();
		}
	}

}
