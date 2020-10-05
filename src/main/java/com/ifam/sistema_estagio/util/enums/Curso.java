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
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	
	public static Curso of(String nomeCurso) {
		return Arrays.asList(Curso.values())
				.stream()
				.filter(curso -> curso.getNomeCurso() == nomeCurso)
				.findFirst()
				.get();
	}
}
