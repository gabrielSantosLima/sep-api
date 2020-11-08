package com.ifam.sistema_estagio.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto implements Serializable {
	private String id;
	private String matricula;
	private String cpf;
	private String nome;
	private String email;
	private FuncaoEstagio tipo;
	private GrauAcademico grau;
}
