package com.ifam.sistema_estagio.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, name = "matricula")
	private String matricula;

	@Column(nullable = false, name = "cpf")
	private String cpf;

	@Column(nullable = false, length = 200, name = "nome")
	private String nome;

	@Column(nullable = false, length = 100, name = "email")
	private String email;

	@Column(nullable = true, name = "funcao_estagio_projeto")
	@Enumerated(EnumType.STRING)
	private FuncaoEstagio tipo;

	@Column(nullable = true, name = "grau")
	@Enumerated(EnumType.STRING)
	private GrauAcademico grau;
		
	@Column(nullable = true, name = "username")
	private String username;

	@Column(nullable = true, name = "password")
	private String password;

	@Column(nullable = true, name = "password_confirm")
	private String passwordConfirm;

}
