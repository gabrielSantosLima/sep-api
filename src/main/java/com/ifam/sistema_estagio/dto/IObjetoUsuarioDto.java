package com.ifam.sistema_estagio.dto;


import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;

import java.io.Serializable;

public interface IObjetoUsuarioDto extends Serializable {
    Aluno construirAluno();
    Professor construirProfessor();
    Coordenadora construirCoordenadora();
}
