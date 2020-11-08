package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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

	public AtaProjetoFields() {
		this.titulo=" ";
		this.media=" ";
		this.data=" ";
		this.mensagem=" ";
		this.membro_1=" ";
		this.membro_2=" ";
		this.presidente=" ";
		this.mediaExtenso=" ";
		this.membroExtra=" ";
	}
}
