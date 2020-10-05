package com.ifam.sistema_estagio.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "papel")
public class Papel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "papel")
	private List<Aluno> alunos;
	
	@OneToMany(mappedBy = "papel")
	private List<Professor> professores;
	
	@OneToMany(mappedBy = "papel")
	private List<Coordenadora> coodernadoras;
	
	@ManyToMany
	@JoinTable(
			  name = "papel_funcao", 
			  joinColumns = @JoinColumn(name = "papel_id"), 
			  inverseJoinColumns = @JoinColumn(name = "funcao_id"))
	private List<Funcao> funcoes;

	public Papel(String name, List<Funcao> funcoes) {
		this.name = name;
		this.funcoes = funcoes;
	}
}
