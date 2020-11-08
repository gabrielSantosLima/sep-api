package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificadoFields{
	
	private final String data;
	private final String mensagem;
	
	public CertificadoFields() {
		this.data = " ";
		this.mensagem = " "; 
	}
}
