package com.ifam.sistema_estagio.reports.fields;

public class FichaDeAvaliacaoProjetoDefesaFields {
	
	private final String autor;
	private final String titulo;
	private final String avaliador;
	private final String data;
	private final String funcao_avaliador;
	private final String nota_qualidade;
	private final String nota_conhecimento;
	private final String nota_clareza;
	private final String nota_linguagem;
	private final String nota_tempo;
	private final String nota_resposta;
	private final String total;
	
	public FichaDeAvaliacaoProjetoDefesaFields() {
		this.autor = "";
		this.titulo = "";
		this.avaliador = "";
		this.data = "";
		this.funcao_avaliador = "";
		this.nota_qualidade = "";
		this.nota_conhecimento = "";
		this.nota_clareza = "";
		this.nota_linguagem = "";
		this.nota_tempo = "";
		this.nota_resposta = "";
		this.total = "";
	}
	
	public FichaDeAvaliacaoProjetoDefesaFields(String autor, String titulo,
			String avaliador, String data, String funcao_avaliador,String nota_qualidade,
			String nota_conhecimento, String nota_clareza, String nota_linguagem,
			String nota_tempo, String nota_resposta, String total) {
		this.autor = autor;
		this.titulo = titulo;
		this.avaliador = avaliador;
		this.data = data;
		this.funcao_avaliador = funcao_avaliador;
		this.nota_qualidade = nota_qualidade;
		this.nota_conhecimento = nota_conhecimento;
		this.nota_clareza = nota_clareza;
		this.nota_linguagem = nota_linguagem;
		this.nota_tempo = nota_tempo;
		this.nota_resposta = nota_resposta;
		this.total = total;
	}
	
	public String getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public String getData() {
		return data;
	}

	public String getFuncao_avaliador() {
		return funcao_avaliador;
	}

	public String getNota_qualidade() {
		return nota_qualidade;
	}

	public String getNota_conhecimento() {
		return nota_conhecimento;
	}

	public String getNota_clareza() {
		return nota_clareza;
	}

	public String getNota_linguagem() {
		return nota_linguagem;
	}

	public String getNota_tempo() {
		return nota_tempo;
	}

	public String getNota_resposta() {
		return nota_resposta;
	}

	public String getTotal() {
		return total;
	}
}
