package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AtaEstagioFields{

	private String titulo;
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
		this.titulo= " ";
		this.media= " ";
		this.horaFinalizado= " ";
		this.data= " ";
		this.coordenadora= " ";
		this.mensagem= " ";
		this.membro_1= " ";
		this.membro_2= " ";
		this.aluno= " ";
		this.curso= " ";
		this.presidente= " ";
		this.mensagemChefe= " ";
	}
}
