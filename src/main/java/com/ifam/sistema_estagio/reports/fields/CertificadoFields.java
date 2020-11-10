package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CertificadoFields{
	private final String data;
	private final String mensagem;
}
