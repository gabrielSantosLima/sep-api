package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FichaDeAvaliacaoEstagioFields {

	private final String nota_conhecimentos;
	private final String nota_organizacao;
	private final String nota_atividades;
	private final String nota_apresentacao;
	private final String soma;
	private final String discente;
	private final String curso;
	private final String data_fim;
	private final String funcao;
	private final String avaliador;
	private final String passou;
	private final String nao_passou;
	private final String data_emissao;
	private final String funcao_avaliador;

	public FichaDeAvaliacaoEstagioFields() {
		this.nota_conhecimentos = " ";
		this.nota_organizacao = " ";
		this.nota_atividades = " ";
		this.nota_apresentacao = " ";
		this.soma = " ";
		this.discente = " ";
		this.curso = " ";
		this.data_fim = " ";
		this.funcao = " ";
		this.avaliador = " ";
		this.passou = " ";
		this.nao_passou = " ";
		this.data_emissao = " ";
		this.funcao_avaliador = " ";
	}
}
