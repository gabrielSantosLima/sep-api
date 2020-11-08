package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FichaDeAvaliacaoProjetoCapaFields{

	private String autor;
	private String titulo;
	private String av1;
	private String av2;
	private String av3;
	private String nota_defesa_av1;
	private String nota_defesa_av2;
	private String nota_defesa_av3;
	private String nota_relatorio_av1;
	private String nota_relatorio_av2;
	private String nota_relatorio_av3;
	private String media_defesa;
	private String media_relatorio;
	private String media;
	private String data;

	public FichaDeAvaliacaoProjetoCapaFields() {
		this.autor=" ";
		this.titulo=" ";
		this.av1=" ";
		this.av2=" ";
		this.av3=" ";
		this.nota_defesa_av1=" ";
		this.nota_defesa_av2=" ";
		this.nota_defesa_av3=" ";
		this.nota_relatorio_av1=" ";
		this.nota_relatorio_av2=" ";
		this.nota_relatorio_av3=" ";
		this.media_defesa=" ";
		this.media_relatorio=" ";
		this.media=" ";
		this.data=" ";
	}
}
