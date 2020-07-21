package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

@MappedSuperclass
public abstract class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, name = "matricula")
	private String matricula;
	
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

	public Usuario() {
		
	}
	
	public Usuario(Integer id, String matricula, String nome, String email, FuncaoEstagio tipo, GrauAcademico grau) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.grau = grau;
	}
	
	public GrauAcademico getGrau() {
		return grau;
	}

	public void setGrau(GrauAcademico grau) {
		this.grau = grau;
	}

	public FuncaoEstagio getTipo() {
		return tipo;
	}

	public void setTipo(FuncaoEstagio tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
