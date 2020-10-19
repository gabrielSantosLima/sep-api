package com.ifam.sistema_estagio.reports;

@SuppressWarnings("unused")
public class FichaDeAvaliacaoProjetoRelatorioFields {

	private String autor;
	private String titulo;
	private String avaliador;
	private String data;
	private String funcao_avaliador;
	private String nota_apresentacao;
	private String nota_abnt;
	private String nota_metodologia;
	private String nota_qualidade;
	private String nota_fundamentacao;
	private String nota_documentacao;
	private String nota_resultados;
	private String total;

	public FichaDeAvaliacaoProjetoRelatorioFields() {
		this.autor = " ";
		this.titulo = " ";
		this.avaliador = " ";
		this.data = " ";
		this.funcao_avaliador = " ";
		this.nota_apresentacao = " ";
		this.nota_abnt = " ";
		this.nota_metodologia = " ";
		this.nota_qualidade = " ";
		this.nota_fundamentacao = " ";
		this.nota_documentacao = " ";
		this.nota_resultados = " ";
		this.total = " ";
	}

	public FichaDeAvaliacaoProjetoRelatorioFields(String autor, String titulo,
			String avaliador, String data, String funcao_avaliador,
			String nota_apresentacao, String nota_abnt, String nota_metodologia,
			String nota_qualidade, String nota_fundamentacao, String nota_documentacao,
			String nota_resultados, String total) {
		this.autor = autor;
		this.titulo = titulo;
		this.avaliador = avaliador;
		this.data = data;
		this.funcao_avaliador = funcao_avaliador;
		this.nota_apresentacao = nota_apresentacao;
		this.nota_abnt = nota_abnt;
		this.nota_metodologia = nota_metodologia;
		this.nota_qualidade = nota_qualidade;
		this.nota_fundamentacao = nota_fundamentacao;
		this.nota_documentacao = nota_documentacao;
		this.nota_resultados = nota_resultados;
		this.total = total;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(String avaliador) {
		this.avaliador = avaliador;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFuncao_avaliador() {
		return funcao_avaliador;
	}

	public void setFuncao_avaliador(String funcao_avaliador) {
		this.funcao_avaliador = funcao_avaliador;
	}

	public String getNota_apresentacao() {
		return nota_apresentacao;
	}

	public void setNota_apresentacao(String nota_apresentacao) {
		this.nota_apresentacao = nota_apresentacao;
	}

	public String getNota_abnt() {
		return nota_abnt;
	}

	public void setNota_abnt(String nota_abnt) {
		this.nota_abnt = nota_abnt;
	}

	public String getNota_metodologia() {
		return nota_metodologia;
	}

	public void setNota_metodologia(String nota_metodologia) {
		this.nota_metodologia = nota_metodologia;
	}

	public String getNota_qualidade() {
		return nota_qualidade;
	}

	public void setNota_qualidade(String nota_qualidade) {
		this.nota_qualidade = nota_qualidade;
	}

	public String getNota_fundamentacao() {
		return nota_fundamentacao;
	}

	public void setNota_fundamentacao(String nota_fundamentacao) {
		this.nota_fundamentacao = nota_fundamentacao;
	}

	public String getNota_documentacao() {
		return nota_documentacao;
	}

	public void setNota_documentacao(String nota_documentacao) {
		this.nota_documentacao = nota_documentacao;
	}

	public String getNota_resultados() {
		return nota_resultados;
	}

	public void setNota_resultados(String nota_resultados) {
		this.nota_resultados = nota_resultados;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
