package com.ifam.sistema_estagio.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GenericService<E, Repository extends JpaRepository<E, Integer>>{

	@Autowired
	private Repository repository;
	
	//Cadastrar
	public E create(E e) throws Exception {
		
		if(e == null) {
			throw new Exception("[service] Erro na criação da entidade!");
		}
		
		return repository.save(e);
	}
	
	//Atualizar
	public E update(Integer id, E e) throws Exception {

		if(e == null) {
			throw new Exception("[service] Erro na atualização da entidade!");
		}
		
		if(!exists(id)) {
			repository.save(e);			
		}
		
		E newE = findById(id).get();
		delete(id);
		
		repository.save(newE);
		
		return newE;
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
		
		if(exists(id)) {
			throw new Exception("[service] Erro ao deletar entidade!");
		}
		
		repository.deleteById(id);
	}
	
}
