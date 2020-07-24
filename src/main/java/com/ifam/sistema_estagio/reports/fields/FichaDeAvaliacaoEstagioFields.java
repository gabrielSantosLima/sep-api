package com.ifam.sistema_estagio.reports.fields;

public class FichaDeAvaliacaoEstagioFields{

	private String nota_conhecimentos;
	private String nota_organizacao;
	private String nota_atividades;
	private String nota_apresentacao;
	private String soma;
	private String discente;
	private String curso;
	private String data_fim;
	private String funcao;
	private String avaliador;
	private String passou;
	private String nao_passou;
	private String data_emissao;
	private String funcao_avaliador;

	public FichaDeAvaliacaoEstagioFields() {

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

	public void setNota_conhecimentos(String nota_conhecimentos) {
		this.nota_conhecimentos = nota_conhecimentos;
	}

	public String getNota_organizacao() {
		return nota_organizacao;
	}

	public void setNota_organizacao(String nota_organizacao) {
		this.nota_organizacao = nota_organizacao;
	}

	public String getNota_atividades() {
		return nota_atividades;
	}

	public void setNota_atividades(String nota_atividades) {
		this.nota_atividades = nota_atividades;
	}

	public String getNota_apresentacao() {
		return nota_apresentacao;
	}

	public void setNota_apresentacao(String nota_apresentacao) {
		this.nota_apresentacao = nota_apresentacao;
	}

	public String getSoma() {
		return soma;
	}

	public void setSoma(String soma) {
		this.soma = soma;
	}

	public String getDiscente() {
		return discente;
	}

	public void setDiscente(String discente) {
		this.discente = discente;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(String avaliador) {
		this.avaliador = avaliador;
	}

	public String getPassou() {
		return passou;
	}

	public void setPassou(String passou) {
		this.passou = passou;
	}

	public String getNao_passou() {
		return nao_passou;
	}

	public void setNao_passou(String nao_passou) {
		this.nao_passou = nao_passou;
	}

	public String getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(String data_emissao) {
		this.data_emissao = data_emissao;
	}

	public String getFuncao_avaliador() {
		return funcao_avaliador;
	}

	public void setFuncao_avaliador(String funcao_avaliador) {
		this.funcao_avaliador = funcao_avaliador;
	}
}