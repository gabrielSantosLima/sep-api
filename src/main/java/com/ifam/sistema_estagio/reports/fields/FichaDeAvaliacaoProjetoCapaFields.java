package com.ifam.sistema_estagio.reports.fields;

public class FichaDeAvaliacaoProjetoCapaFields{

	private String autor;
	private String titulo;
	private String av1;
	private String av2;
	private String av3;
	private String nota_defesa_av1;
	private String nota_defesa_av2;
	private String nota_defesa_av3;
	private String nota_relatorio_av1;
	private String nota_relatorio_av2;
	private String nota_relatorio_av3;
	private String media_defesa;
	private String media_relatorio;
	private String media;
	private String data;

	public FichaDeAvaliacaoProjetoCapaFields() {
		this.autor=" ";
		this.titulo=" ";
		this.av1=" ";
		this.av2=" ";
		this.av3=" ";
		this.nota_defesa_av1=" ";
		this.nota_defesa_av2=" ";
		this.nota_defesa_av3=" ";
		this.nota_relatorio_av1=" ";
		this.nota_relatorio_av2=" ";
		this.nota_relatorio_av3=" ";
		this.media_defesa=" ";
		this.media_relatorio=" ";
		this.media=" ";
		this.data=" ";
	}

	public FichaDeAvaliacaoProjetoCapaFields(String autor, String titulo, String av1,
			String av2, String av3, String nota_defesa_av1, String nota_defesa_av2,
			String nota_defesa_av3, String nota_relatorio_av1, String nota_relatorio_av2,
			String nota_relatorio_av3, String media_defesa, String media_relatorio,
			String media, String data) {
		this.autor = autor;
		this.titulo = titulo;
		this.av1 = av1;
		this.av2 = av2;
		this.av3 = av3;
		this.nota_defesa_av1 = nota_defesa_av1;
		this.nota_defesa_av2 = nota_defesa_av2;
		this.nota_defesa_av3 = nota_defesa_av3;
		this.nota_relatorio_av1 = nota_relatorio_av1;
		this.nota_relatorio_av2 = nota_relatorio_av2;
		this.nota_relatorio_av3 = nota_relatorio_av3;
		this.media_defesa = media_defesa;
		this.media_relatorio = media_relatorio;
		this.media = media;
		this.data = data;
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

	public String getAv1() {
		return av1;
	}

	public void setAv1(String av1) {
		this.av1 = av1;
	}

	public String getAv2() {
		return av2;
	}

	public void setAv2(String av2) {
		this.av2 = av2;
	}

	public String getAv3() {
		return av3;
	}

	public void setAv3(String av3) {
		this.av3 = av3;
	}

	public String getNota_defesa_av1() {
		return nota_defesa_av1;
	}

	public void setNota_defesa_av1(String nota_defesa_av1) {
		this.nota_defesa_av1 = nota_defesa_av1;
	}

	public String getNota_defesa_av2() {
		return nota_defesa_av2;
	}

	public void setNota_defesa_av2(String nota_defesa_av2) {
		this.nota_defesa_av2 = nota_defesa_av2;
	}

	public String getNota_defesa_av3() {
		return nota_defesa_av3;
	}

	public void setNota_defesa_av3(String nota_defesa_av3) {
		this.nota_defesa_av3 = nota_defesa_av3;
	}

	public String getNota_relatorio_av1() {
		return nota_relatorio_av1;
	}

	public void setNota_relatorio_av1(String nota_relatorio_av1) {
		this.nota_relatorio_av1 = nota_relatorio_av1;
	}

	public String getNota_relatorio_av2() {
		return nota_relatorio_av2;
	}

	public void setNota_relatorio_av2(String nota_relatorio_av2) {
		this.nota_relatorio_av2 = nota_relatorio_av2;
	}

	public String getNota_relatorio_av3() {
		return nota_relatorio_av3;
	}

	public void setNota_relatorio_av3(String nota_relatorio_av3) {
		this.nota_relatorio_av3 = nota_relatorio_av3;
	}

	public String getMedia_defesa() {
		return media_defesa;
	}

	public void setMedia_defesa(String media_defesa) {
		this.media_defesa = media_defesa;
	}

	public String getMedia_relatorio() {
		return media_relatorio;
	}

	public void setMedia_relatorio(String media_relatorio) {
		this.media_relatorio = media_relatorio;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
