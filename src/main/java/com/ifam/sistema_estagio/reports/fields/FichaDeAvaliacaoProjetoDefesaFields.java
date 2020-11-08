package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FichaDeAvaliacaoProjetoDefesaFields {
	
	private final String autor;
	private final String titulo;
	private final String avaliador;
	private final String data;
	private final String funcao_avaliador;
	private final String nota_qualidade;
	private final String nota_conhecimento;
	private final String nota_clareza;
	private final String nota_linguagem;
	private final String nota_tempo;
	private final String nota_resposta;
	private final String total;
	
	public FichaDeAvaliacaoProjetoDefesaFields() {
		this.autor = "";
		this.titulo = "";
		this.avaliador = "";
		this.data = "";
		this.funcao_avaliador = "";
		this.nota_qualidade = "";
		this.nota_conhecimento = "";
		this.nota_clareza = "";
		this.nota_linguagem = "";
		this.nota_tempo = "";
		this.nota_resposta = "";
		this.total = "";
	}
}
