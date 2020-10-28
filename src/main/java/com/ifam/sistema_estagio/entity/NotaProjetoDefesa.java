package com.ifam.sistema_estagio.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaProjetoDefesa {

	@Column(name = "nota_slide")
	private Integer notaSlide;

	@Column(name = "nota_assunto")
	private Integer notaAssunto;

	@Column(name = "nota_clareza")
	private Integer notaClareza;

	@Column(name = "nota_linguagem")
	private Integer notaLinguagem;

	@Column(name = "nota_tempo")
	private Integer notaTempo;

	@Column(name = "nota_respotas")
	private Integer notaRespostas;
}
