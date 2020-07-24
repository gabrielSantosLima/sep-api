package com.ifam.sistema_estagio.reports.fields;

public class AtaEstagioFields{

	private String media;
	private String horaFinalizado;
	private String data;
	private String coordenadora;
	private String mensagem;
	private String membro_1;
	private String membro_2;
	private String aluno;
	private String curso;
	private String presidente;
	private String mensagemChefe;

	public AtaEstagioFields() {

	}

	public AtaEstagioFields(String media, String horaFinalizado, String data,
			String coordenadora, String mensagem, String membro_1, String membro_2,
			String aluno, String curso, String presidente, String mensagemChefe) {
		this.media = media;
		this.horaFinalizado = horaFinalizado;
		this.data = data;
		this.coordenadora = coordenadora;
		this.mensagem = mensagem;
		this.membro_1 = membro_1;
		this.membro_2 = membro_2;
		this.aluno = aluno;
		this.curso = curso;
		this.presidente = presidente;
		this.mensagemChefe = mensagemChefe;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getHoraFinalizado() {
		return horaFinalizado;
	}

	public void setHoraFinalizado(String horaFinalizado) {
		this.horaFinalizado = horaFinalizado;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCoordenadora() {
		return coordenadora;
	}

	public void setCoordenadora(String coordenadora) {
		this.coordenadora = coordenadora;
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

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getMensagemChefe() {
		return mensagemChefe;
	}

	public void setMensagemChefe(String mensagemChefe) {
		this.mensagemChefe = mensagemChefe;
	}
}
