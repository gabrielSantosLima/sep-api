package com.ifam.sistema_estagio.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaProjetoDefesa {

	@Column(name = "nota_slide")
	private Double notaSlide;

	@Column(name = "nota_assunto")
	private Double notaAssunto;

	@Column(name = "nota_clareza")
	private Double notaClareza;

	@Column(name = "nota_linguagem")
	private Double notaLinguagem;

	@Column(name = "nota_tempo")
	private Double notaTempo;

	@Column(name = "nota_respotas")
	private Double notaRespostas;
}
