package com.ifam.sistema_estagio.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "avaliadores")
public class Avaliadores {

	@EmbeddedId
	private ChavePrimaria id;

	@ManyToOne
	@MapsId("professor_id")
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;

	@ManyToOne
	@MapsId("banca_id")
	@JoinColumn(name = "banca_id", nullable = false)
	private Banca banca;

	public Avaliadores(Professor professor, Banca banca) {
		this.banca = banca;
		this.professor = professor;
	}
}

@Embeddable
class ChavePrimaria implements Serializable{

	private static final long serialVersionUID = 7552172921230408228L;

	@Column(name = "professor_id")
	private Long professorId;

	@Column(name = "banca_id")
	private Long bancaId;
}
