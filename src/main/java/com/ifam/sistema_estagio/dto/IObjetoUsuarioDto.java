package com.ifam.sistema_estagio.dto;


import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;

public interface IObjetoUsuarioDto{
    Aluno construirAluno();
    Professor construirProfessor();
    Coordenadora construirCoordenadora();
}
