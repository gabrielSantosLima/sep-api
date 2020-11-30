package com.ifam.sistema_estagio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService extends GenericService<Professor, ProfessorRepository> {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> encontrarPorNome(String nome){
        return professorRepository.findByNomeContainingIgnoreCase(nome);
    }
}
