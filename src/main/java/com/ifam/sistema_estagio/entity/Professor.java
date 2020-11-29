package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professor")
public class Professor extends Usuario{

	@ManyToMany
	private List<Banca> bancas;

	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoEstagio> fichaEstagios;

	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoProjeto> fichaProjeto;

	@OneToMany(mappedBy = "responsavel")
	private List<EstagioPCCT> estagiosPcct;
}
