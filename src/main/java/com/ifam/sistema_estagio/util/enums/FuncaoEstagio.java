package com.ifam.sistema_estagio.util.enums;

public enum FuncaoEstagio {
	
	DISCENTE(0),
	COORDENADOR(1),
	COORDENADOR_ADJUNTO(2),
	COLABORADOR(3);
	
	private Integer valor;
	
	FuncaoEstagio(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
}
