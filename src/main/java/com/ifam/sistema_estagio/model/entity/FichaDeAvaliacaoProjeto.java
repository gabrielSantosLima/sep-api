package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ficha_de_avaliacao_projeto")
public class FichaDeAvaliacaoProjeto extends FichaDeAvaliacao{
	
	@Embedded
	private NotaProjetoDefesa notaDefesa;

	@Embedded
	private NotaProjetoTrabalho notaTrabalho;
}
