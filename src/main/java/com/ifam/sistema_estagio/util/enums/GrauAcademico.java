package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;
import java.util.List;

public enum GrauAcademico {
	
	GRADUADO("Graduado"),
	MESTRE("Mestre"),
	DOUTOR("Doutor"),
	POS_DOUTOR("Pós-Doc");
	
	private String grau;
	
	GrauAcademico(String grau){
		this.grau = grau;
	}
	
	public String getGrau() {
		return grau;
	}
	
	public static GrauAcademico of(String grau) {
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
