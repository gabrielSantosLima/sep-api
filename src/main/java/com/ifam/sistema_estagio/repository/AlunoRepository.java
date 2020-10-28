package com.ifam.sistema_estagio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.EstagioPCCT;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
	List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct);
	
	Optional<Aluno> findByUsername(String username);
}
