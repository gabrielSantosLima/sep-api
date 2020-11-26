package com.ifam.sistema_estagio.services;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.ProfessorRepository;

@Service
public class ProfessorService extends GenericService<Professor, ProfessorRepository> {}
