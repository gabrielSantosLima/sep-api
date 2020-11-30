package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professor")
public class Professor extends Usuario{

	@JsonBackReference
	@ManyToMany
	private List<Banca> bancas;

	@JsonBackReference
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoEstagio> fichaEstagios;

	@JsonBackReference
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoProjeto> fichaProjeto;

	@JsonBackReference
	@OneToMany(mappedBy = "responsavel")
	private List<EstagioPCCT> estagiosPcct;
}
