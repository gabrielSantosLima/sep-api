package com.ifam.sistema_estagio.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@Table(name = "fichas_de_avaliacoes_projeto")
public class FichaDeAvaliacaoProjeto{

	@Id
	@GeneratedValue(generator = IdentificadorHexadecimalGenerator.nome)
	@GenericGenerator(
			name = IdentificadorHexadecimalGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator"
	)
	@Column(length = ManipularNumerosHexadecimais.TAMANHO_NUMERO_ALEATORIO)
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
