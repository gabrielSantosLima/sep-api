package com.ifam.sistema_estagio.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<E, Repository extends JpaRepository<E, Integer>>{

	@Autowired
	private Repository repository;
	
	//Cadastrar
	public E create(E e) throws Exception {
		
		if(e == null) {
			throw new Exception("[service] Entidade nula!");
		}
		
		return repository.save(e);
	}
	
	//Atualizar
	public E update(Integer id, E newE) throws Exception {

		if(newE == null) {
			throw new Exception("[service] Entidade nula!");
		}
		
		if(!exists(id)) {
			throw new Exception("[service] Entidade não existe!");
		}
		
		
		E updatedE = repository.save(newE);
		
		return updatedE;
	}
	
	//Listar
	public List<E> list() {
		return repository.findAll();
	}
	
	//Buscar por Id
	public Optional<E> findById(Integer id) {
		return repository.findById(id);
	}
	
	//exists
	public boolean exists(Integer id) {
		return repository.existsById(id);
	}
	
	//Excluir
	public void delete(Integer id) throws Exception {
		
		if(!exists(id)) {
			throw new Exception("[service] Entidade não existe!");
		}
		
		repository.deleteById(id);
	}
	
}
