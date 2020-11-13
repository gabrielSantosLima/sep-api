package com.ifam.sistema_estagio.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "ficha_de_avaliacao_estagio")
public class FichaDeAvaliacaoEstagio extends FichaDeAvaliacao{

	//Avaliador
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	//Ata a qual pertence
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = true)
	private Ata ata;
	
	@Embedded
	private NotaEstagio nota;
}
