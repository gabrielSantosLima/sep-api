package com.ifam.sistema_estagio.services;

import java.util.Optional;
import java.util.List;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public class GenericService<E, R extends JpaRepository>{

	@Autowired
	private R repository;

	@Transactional
	public E salvar(E e) throws Exception {
		if(e == null) throw new Exception("Entidade nula");
		return (E) repository.save(e);
	}

	@Transactional
	public E atualizar(String id, E newE) throws Exception {
		if(newE == null) throw new Exception("Entidade nula");
		if(!existe(id)) throw new Exception("Entidade não existe!");
		val updatedE = (E) repository.save(newE);
		return updatedE;
	}

	@Transactional
	public void deletar(String id) throws Exception {
		if(!existe(id)) throw new Exception("Entidade não existe");
		repository.deleteById(id);
	}

	public List<E> listar() {
		return repository.findAll();
	}

	public Optional<E> encontrarPorId(String id) {
		return repository.findById(id);
	}

	public boolean existe(String id) {
		return repository.existsById(id);
	}
}
