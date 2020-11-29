package com.ifam.sistema_estagio.controller;

import com.ifam.sistema_estagio.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
@SuppressWarnings("unused")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
}
