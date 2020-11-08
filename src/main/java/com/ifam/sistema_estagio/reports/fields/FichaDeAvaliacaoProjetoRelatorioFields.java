package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FichaDeAvaliacaoProjetoRelatorioFields {

	private String autor;
	private String titulo;
	private String avaliador;
	private String data;
	private String funcao_avaliador;
	private String nota_apresentacao;
	private String nota_abnt;
	private String nota_metodologia;
	private String nota_qualidade;
	private String nota_fundamentacao;
	private String nota_documentacao;
	private String nota_resultados;
	private String total;

	public FichaDeAvaliacaoProjetoRelatorioFields() {
		this.autor = " ";
		this.titulo = " ";
		this.avaliador = " ";
		this.data = " ";
		this.funcao_avaliador = " ";
		this.nota_apresentacao = " ";
		this.nota_abnt = " ";
		this.nota_metodologia = " ";
		this.nota_qualidade = " ";
		this.nota_fundamentacao = " ";
		this.nota_documentacao = " ";
		this.nota_resultados = " ";
		this.total = " ";
	}
}
