package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;

public enum ModalidadeCurso {
	
	INTEGRADO(0),
	GRADUAÇÃO(1),
	TECNICO(2),
	RESIDENTE(3),
	MESTRADO(4),	
	DOUTORADO(5),;	
	
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
