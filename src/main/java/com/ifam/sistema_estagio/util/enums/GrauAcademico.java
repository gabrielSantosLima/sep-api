package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;
import java.util.List;

public enum GrauAcademico {
	
	ENSINO_FUNDAMENTAL(0),
	ENSINO_MEDIO(1),
	GRADUADO(2),
	MESTRE(3),
	DOUTOR(4);
	
	private Integer grau;
	
	GrauAcademico(Integer grau){
		this.grau = grau;
	}
	
	public Integer getGrau() {
		return grau;
	}
	
	public static GrauAcademico of(Integer grau) {
		return Arrays.asList(GrauAcademico.values())
				.stream()
				.filter(g -> g.getGrau() == grau)
				.findFirst()
				.get();
	}
	
	public static List<GrauAcademico> all() {
		return Arrays.asList(GrauAcademico.values());
	}
}
