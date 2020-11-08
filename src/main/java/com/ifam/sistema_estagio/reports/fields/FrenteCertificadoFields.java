package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FrenteCertificadoFields{

	private String titulo;
	private String autores;
	private String data;
	private String participantes;

	public FrenteCertificadoFields(){
		this.titulo = " ";
		this.autores = " ";
		this.data = " ";
		this.participantes = " ";
	}
}
