package com.ifam.sistema_estagio.reports.fields;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FichaDeAvaliacaoEstagioFields {
	private final String nota_conhecimentos;
	private final String nota_organizacao;
	private final String nota_atividades;
	private final String nota_apresentacao;
	private final String soma;
	private final String discente;
	private final String curso;
	private final String ano_finalizacao;
	private final String funcao_discente;
	private final String avaliador;
	private final String passou;
	private final String nao_passou;
	private final String data_emissao;
	private final String funcao_avaliador;
}
