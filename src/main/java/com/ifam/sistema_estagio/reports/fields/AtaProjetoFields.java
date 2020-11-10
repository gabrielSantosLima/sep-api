package com.ifam.sistema_estagio.reports.fields;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AtaProjetoFields{
	private String titulo;
	private String media;
	private String data;
	private String mensagem;
	private String membro_1;
	private String membro_2;
	private String presidente;
	private String mediaExtenso;
	private String membroExtra;
}
