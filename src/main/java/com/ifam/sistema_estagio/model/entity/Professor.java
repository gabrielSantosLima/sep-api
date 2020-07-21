package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifam.sistema_estagio.model.entity.interfaces.UsuarioLogavel;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

@Entity
@Table(name = "professor")
public class Professor extends Usuario implements UsuarioLogavel {

	
	@Column(nullable = true, name = "username")
	private String username;

	@Column(nullable = true, name = "password")
	private String password;

	@Column(nullable = true, name = "password_confirm")
	private String passwordConfirm;

	// Avaliadores da banca
	@OneToMany(mappedBy = "professor")
	private List<Avaliadores> avaliadores;

	// Fichas de avaliação de estágios
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoEstagio> fichaEstagios;

	// Fichas de avaliação de projetos
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoProjeto> fichaProjeto;

	// Projetos ou estágios
	@OneToMany(mappedBy = "responsavel")
	private List<EstagioPCCT> estagiosPcct;

	@ManyToMany
	private Set<Role> roles;

	public Professor() {
		super();
	}
	
	public Professor(Integer id, 
			String matricula, 
			String nome, 
			String email, 
			String username, 
			String password,
			String passwordConfirm, 
			FuncaoEstagio tipo, 
			GrauAcademico grau, 
			List<Avaliadores> avaliadores,
			List<FichaDeAvaliacaoEstagio> fichaEstagios, 
			List<FichaDeAvaliacaoProjeto> fichaProjeto,
			List<EstagioPCCT> estagiosPcct, 
			Set<Role> roles
		) {
		super(id, matricula, nome, email, tipo, grau);
		this.avaliadores = avaliadores;
		this.fichaEstagios = fichaEstagios;
		this.fichaProjeto = fichaProjeto;
		this.estagiosPcct = estagiosPcct;
		this.roles = roles;
	}
	
	public List<Avaliadores> getAvaliadores() {
		return avaliadores;
	}

	public void setAvaliadores(List<Avaliadores> avaliadores) {
		this.avaliadores = avaliadores;
	}

	public List<FichaDeAvaliacaoEstagio> getFichaEstagios() {
		return fichaEstagios;
	}

	public void setFichaEstagios(List<FichaDeAvaliacaoEstagio> fichaEstagios) {
		this.fichaEstagios = fichaEstagios;
	}

	public List<FichaDeAvaliacaoProjeto> getFichaProjeto() {
		return fichaProjeto;
	}

	public void setFichaProjeto(List<FichaDeAvaliacaoProjeto> fichaProjeto) {
		this.fichaProjeto = fichaProjeto;
	}

	public List<EstagioPCCT> getEstagiosPcct() {
		return estagiosPcct;
	}

	public void setEstagiosPcct(List<EstagioPCCT> estagiosPcct) {
		this.estagiosPcct = estagiosPcct;
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
