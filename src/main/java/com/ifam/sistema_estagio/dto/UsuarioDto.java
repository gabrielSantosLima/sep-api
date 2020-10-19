package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto {

	private String matricula;

	private String cpf;

	private String nome;

	private String email;

	private FuncaoEstagio tipo;

	private GrauAcademico grau;
	
}
