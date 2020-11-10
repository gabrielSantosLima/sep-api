package com.ifam.sistema_estagio.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public class GenericService<E, R extends JpaRepository>{

	@Autowired
	private R repository;

	@Transactional
	public E salvar(E e) throws Exception {
		if(e == null) throw new Exception("[service] Entidade nula!");
		return (E) repository.save(e);
	}

	@Transactional
	public E atualizar(Integer id, E newE) throws Exception {
		if(newE == null) throw new Exception("[service] Entidade nula!");
		if(!existe(id)) throw new Exception("[service] Entidade não existe!");

		E updatedE = (E) repository.save(newE);
		return updatedE;
	}

	@Transactional
	public void deletar(Integer id) throws Exception {
		if(!existe(id)) throw new Exception("[service] Entidade não existe!");
		repository.deleteById(id);
	}

	public List<E> listar() {
		return repository.findAll();
	}

	public Optional<E> encontrarPorId(Integer id) {
		return repository.findById(id);
	}

	public boolean existe(Integer id) {
		return repository.existsById(id);
	}
}
