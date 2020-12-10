package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Coordenadora;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoordenadoraRepository extends JpaRepository<Coordenadora, String>{
	List<Coordenadora> findByNomeContainingIgnoreCase(String nome);
	List<Coordenadora> findByMatriculaContainingIgnoreCase(String matricula);
	Optional<Coordenadora> findByCpf(String cpf);
}
