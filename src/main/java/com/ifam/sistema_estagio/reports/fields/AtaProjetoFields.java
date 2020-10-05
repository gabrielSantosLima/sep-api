package com.ifam.sistema_estagio.reports.fields;

public class AtaProjetoFields{

	private String titulo;
	private String media;
	private String data;
	private String mensagem;
	private String membro_1;
	private String membro_2;
	private String presidente;
	private String mediaExtenso;
	private String membroExtra;

	public AtaProjetoFields() {
		this.titulo=" ";
		this.media=" ";
		this.data=" ";
		this.mensagem=" ";
		this.membro_1=" ";
		this.membro_2=" ";
		this.presidente=" ";
		this.mediaExtenso=" ";
		this.membroExtra=" ";
	}

	public AtaProjetoFields(String titulo, String media, String data, String mensagem, String membro_1,
			String membro_2, String presidente, String mediaExtenso, String membroExtra) {
		this.media = media;
		this.data = data;
		this.mensagem = mensagem;
		this.membro_1 = membro_1;
		this.membro_2 = membro_2;
		this.presidente = presidente;
		this.mediaExtenso = mediaExtenso;
		this.membroExtra = membroExtra;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMembro_1() {
		return membro_1;
	}

	public void setMembro_1(String membro_1) {
		this.membro_1 = membro_1;
	}

	public String getMembro_2() {
		return membro_2;
	}

	public void setMembro_2(String membro_2) {
		this.membro_2 = membro_2;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getMediaExtenso() {
		return mediaExtenso;
	}

	public void setMediaExtenso(String mediaExtenso) {
		this.mediaExtenso = mediaExtenso;
	}

	public String getMembroExtra() {
		return membroExtra;
	}

	public void setMembroExtra(String membroExtra) {
		this.membroExtra = membroExtra;
	}
}
