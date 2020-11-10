package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
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
}
