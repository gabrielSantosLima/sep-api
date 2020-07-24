package com.ifam.sistema_estagio.reports.fields;

public class FichaDeAvaliacaoProjetoDefesaFields {
	
	private String autor;
	private String titulo;
	private String avaliador;
	private String data;
	private String funcao_avaliador;
	
	public FichaDeAvaliacaoProjetoDefesaFields() {
		
	}
	
	public FichaDeAvaliacaoProjetoDefesaFields(String autor, String titulo,
			String avaliador, String data, String funcao_avaliador) {
		this.autor = autor;
		this.titulo = titulo;
		this.avaliador = avaliador;
		this.data = data;
		this.funcao_avaliador = funcao_avaliador;
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
}
