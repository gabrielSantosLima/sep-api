package com.ifam.sistema_estagio.model.entity;

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
public class NotaProjetoTrabalho {

	@Column(name = "nota_apresentacao")
	private Integer notaApresentacao;

	@Column(name = "nota_abnt")
	private Integer notaABNT;

	@Column(name = "nota_metodologia")
	private Integer notaMetodologia;

	@Column(name = "nota_conteudo")
	private Integer notaConteudo;

	@Column(name = "nota_Fund")
	private Integer notaFund;

	@Column(name = "nota_diagramas")
	private Integer notaDiagramas;
	
	@Column(name = "nota_resultados")
	private Integer notaResultados;
}
