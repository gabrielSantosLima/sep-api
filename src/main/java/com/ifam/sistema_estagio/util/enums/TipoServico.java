package com.ifam.sistema_estagio.util.enums;

public enum TipoServico {
	
	ESTAGIO("Estágio"),
	PROJETO("Projeto");
	
	private String valor;
	
	TipoServico(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
