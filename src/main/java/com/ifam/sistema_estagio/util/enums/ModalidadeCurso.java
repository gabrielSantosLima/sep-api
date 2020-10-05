package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;

public enum ModalidadeCurso {
	
	INTEGRADO(0),
	SUBSEQUENTE(1),
	GRADUAÇÃO(2),
	MESTRADO(3),	
	DOUTORADO(4),;	
	
	private Integer grau;
	
	ModalidadeCurso(Integer grau){
		this.grau = grau;
	}
	
	public Integer getGrau() {
		return grau;
	}
	
	public static ModalidadeCurso of(Integer grau) {
		return Arrays.asList(ModalidadeCurso.values())
				.stream()
				.filter(g -> g.getGrau() == grau)
				.findFirst()
				.get();
	}
}
