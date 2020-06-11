package com.ifam.sistema_estagio.util.enums;

public enum TipoServico {
	
	ESTAGIO(0),
	PROJETO(1);
	
	private Integer valor;
	
	TipoServico(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public static class Values{
		public static final String ESTAGIO = "0";
		public static final String PROJETO = "1";
	}
}
