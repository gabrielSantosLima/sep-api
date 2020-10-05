package com.ifam.sistema_estagio.util.enums;

public enum FuncaoEstagio {
	
	COORDENADOR(0),
	COORDENADOR_ADJUNTO(1),
	COLABORADOR(2);
	
	private Integer valor;
	
	FuncaoEstagio(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
}
