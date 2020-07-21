package com.ifam.sistema_estagio.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifam.sistema_estagio.model.entity.interfaces.UsuarioLogavel;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

@Entity
@Table(name = "coordenadora")
public class Coordenadora extends Usuario implements UsuarioLogavel{

	@Column(nullable = true, name = "username")
	private String username;

	@Column(nullable = true, name = "password")
	private String password;

	@Column(nullable = true, name = "password_confirm")
	private String passwordConfirm;
	
	//Bancas
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;
	
	@ManyToMany
	private Set<Role> roles;

	public Coordenadora(){
		super();
	}
	
	public Coordenadora(Integer id, 
			String matricula, 
			String nome, 
			String email, 
			FuncaoEstagio tipo, 
			List<Banca> bancas, 
			Set<Role> roles,
			GrauAcademico grau
		) {
		super(id, matricula, nome, email, tipo, grau);
		this.bancas = bancas;
		this.roles = roles;
	}

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
