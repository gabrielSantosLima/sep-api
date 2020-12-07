package com.ifam.sistema_estagio.repository;

import java.util.List;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String>{
	List<Aluno> findByNomeContainingIgnoreCase(String nome);
	List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct);
}
