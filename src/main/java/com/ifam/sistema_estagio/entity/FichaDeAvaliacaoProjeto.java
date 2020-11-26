package com.ifam.sistema_estagio.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "ficha_de_avaliacao_projeto")
public class FichaDeAvaliacaoProjeto{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = false)
	private Ata ata;
	
	@Embedded
	private NotaProjetoDefesa notaDefesa;

	@Embedded
	private NotaProjetoTrabalho notaTrabalho;
}
