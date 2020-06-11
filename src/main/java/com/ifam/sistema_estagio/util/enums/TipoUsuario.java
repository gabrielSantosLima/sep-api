package com.ifam.sistema_estagio.util.enums;

public enum TipoUsuario {
	
	PROFESSOR(0),
	ALUNO(1),
	COORDENADORA(2);
	
	private Integer valor;
	
	TipoUsuario(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public static class Values{
		public static final String PROFESSOR = "0";
		public static final String ALUNO = "1";
		public static final String COORDENADORA = "2";
	}
}
