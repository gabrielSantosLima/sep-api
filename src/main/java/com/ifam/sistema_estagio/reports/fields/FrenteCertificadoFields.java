package com.ifam.sistema_estagio.reports.fields;

public class FrenteCertificadoFields{

	private String titulo;
	private String autores;
	private String data;
	private String participantes;

	public FrenteCertificadoFields() {

	}

	public FrenteCertificadoFields(String titulo, String autores, String data,
			String participantes) {
		this.titulo = titulo;
		this.autores = autores;
		this.data = data;
		this.participantes = participantes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getParticipantes() {
		return participantes;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}
}
