package com.ifam.sistema_estagio.util.enums;

public enum FuncaoEstagio {
	
	COORDENADOR(0),
	COORDENADOR_ADJUNTO(1),
	COLABORADOR(2),
	ADM(3);
	
	private Integer valor;
	
	FuncaoEstagio(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public static class Values{
		public static final String COORDENADOR = "0";
		public static final String COORDENADOR_ADJUNTO = "1";
		public static final String COLABORADOR = "2";
		public static final String ADM = "3";
	}
}
