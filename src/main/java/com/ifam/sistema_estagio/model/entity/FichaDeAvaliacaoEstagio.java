package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ficha_de_avaliacao_estagio")
public class FichaDeAvaliacaoEstagio extends FichaDeAvaliacao{
	
	@Embedded
	private NotaEstagio nota;
}
