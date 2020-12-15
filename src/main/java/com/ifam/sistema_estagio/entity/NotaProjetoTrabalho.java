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
public class NotaProjetoTrabalho {

	@Column(name = "nota_apresentacao")
	private Double notaApresentacao;

	@Column(name = "nota_abnt")
	private Double notaABNT;

	@Column(name = "nota_metodologia")
	private Double notaMetodologia;

	@Column(name = "nota_conteudo")
	private Double notaConteudo;

	@Column(name = "nota_Fund")
	private Double notaFund;

	@Column(name = "nota_diagramas")
	private Double notaDiagramas;
	
	@Column(name = "nota_resultados")
	private Double notaResultados;
}
