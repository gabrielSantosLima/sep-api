package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
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

	public Integer getNotaApresentacao() {
		return notaApresentacao;
	}

	public void setNotaApresentacao(Integer notaApresentacao) {
		this.notaApresentacao = notaApresentacao;
	}

	public Integer getNotaABNT() {
		return notaABNT;
	}

	public void setNotaABNT(Integer notaABNT) {
		this.notaABNT = notaABNT;
	}

	public Integer getNotaMetodologia() {
		return notaMetodologia;
	}

	public void setNotaMetodologia(Integer notaMetodologia) {
		this.notaMetodologia = notaMetodologia;
	}

	public Integer getNotaConteudo() {
		return notaConteudo;
	}

	public void setNotaConteudo(Integer notaConteudo) {
		this.notaConteudo = notaConteudo;
	}

	public Integer getNotaFund() {
		return notaFund;
	}

	public void setNotaFund(Integer notaFund) {
		this.notaFund = notaFund;
	}

	public Integer getNotaDiagramas() {
		return notaDiagramas;
	}

	public void setNotaDiagramas(Integer notaDiagramas) {
		this.notaDiagramas = notaDiagramas;
	}

	public Integer getNotaResultados() {
		return notaResultados;
	}

	public void setNotaResultados(Integer notaResultados) {
		this.notaResultados = notaResultados;
	}
}
