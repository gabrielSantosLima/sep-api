package com.ifam.sistema_estagio.reports;

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

	public FichaDeAvaliacaoEstagioFields(String nota_conhecimentos,
			String nota_organizacao, String nota_atividades, String nota_apresentacao,
			String soma, String discente, String curso, String data_fim, String funcao,
			String avaliador, String passou, String nao_passou, String data_emissao,
			String funcao_avaliador) {
		this.nota_conhecimentos = nota_conhecimentos;
		this.nota_organizacao = nota_organizacao;
		this.nota_atividades = nota_atividades;
		this.nota_apresentacao = nota_apresentacao;
		this.soma = soma;
		this.discente = discente;
		this.curso = curso;
		this.data_fim = data_fim;
		this.funcao = funcao;
		this.avaliador = avaliador;
		this.passou = passou;
		this.nao_passou = nao_passou;
		this.data_emissao = data_emissao;
		this.funcao_avaliador = funcao_avaliador;
	}

	public String getNota_conhecimentos() {
		return nota_conhecimentos;
	}

	public String getNota_organizacao() {
		return nota_organizacao;
	}

	public String getNota_atividades() {
		return nota_atividades;
	}

	public String getNota_apresentacao() {
		return nota_apresentacao;
	}

	public String getSoma() {
		return soma;
	}

	public String getDiscente() {
		return discente;
	}

	public String getCurso() {
		return curso;
	}

	public String getData_fim() {
		return data_fim;
	}

	public String getFuncao() {
		return funcao;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public String getPassou() {
		return passou;
	}

	public String getNao_passou() {
		return nao_passou;
	}

	public String getData_emissao() {
		return data_emissao;
	}

	public String getFuncao_avaliador() {
		return funcao_avaliador;
	}
}
