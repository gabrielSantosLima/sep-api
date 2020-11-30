package com.ifam.sistema_estagio.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.config.HexIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@Table(name = "ficha_de_avaliacao_projeto")
public class FichaDeAvaliacaoProjeto{

	@Id
	@GeneratedValue(generator = HexIdGenerator.nome)
	@GenericGenerator(
			name = HexIdGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.HexIdGenerator"
	)
	@Column(length = 24)
	private String id;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = false)
	private Ata ata;
	
	@Embedded
	private NotaProjetoDefesa notaDefesa;

	@Embedded
	private NotaProjetoTrabalho notaTrabalho;
}
