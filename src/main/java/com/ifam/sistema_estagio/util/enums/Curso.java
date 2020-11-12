package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;

public enum Curso {
	INF("Informática"),
	IQUI("Química"),
	IELT("Eletrotécnica"),
	IEDF("Edificações"),
	IMEC("Mecânica");
	
	private String nomeCurso;
	
	Curso(String nomeCurso ){
		this.nomeCurso = nomeCurso;
	}
	
	public String retornarNomeCurso() {
		return nomeCurso;
	}

	public String retornarNomeCurso(ModalidadeCurso modalidadeCurso) {
		String modalidadeFormatada = modalidadeCurso.name().toLowerCase();
		return "Curso Técnico "+ modalidadeFormatada +" de "+ nomeCurso;
	}
}
