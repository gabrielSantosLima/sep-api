package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Embedded;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ficha_de_avaliacao_projeto")
public class FichaDeAvaliacaoProjeto extends FichaDeAvaliacao{
	
	//Avaliador
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	//Ata a qual pertence
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = false)
	private Ata ata;
	
	@Embedded
	private NotaProjetoDefesa notaDefesa;

	@Embedded
	private NotaProjetoTrabalho notaTrabalho;
}
