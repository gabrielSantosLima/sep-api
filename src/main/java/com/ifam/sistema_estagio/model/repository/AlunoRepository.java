package com.ifam.sistema_estagio.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
	List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct);
}
