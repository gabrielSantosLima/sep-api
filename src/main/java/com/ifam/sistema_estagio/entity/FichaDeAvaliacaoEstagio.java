package com.ifam.sistema_estagio.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "ficha_de_avaliacao_estagio")
public class FichaDeAvaliacaoEstagio{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = true)
	private Ata ata;
	
	@Embedded
	private NotaEstagio nota;
}
