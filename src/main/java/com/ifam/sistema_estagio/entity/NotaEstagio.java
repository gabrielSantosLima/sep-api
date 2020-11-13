package com.ifam.sistema_estagio.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
public class NotaEstagio {
	
	@Column(name = "nota_conhecimento")
	private Double notaConhecimento;

	@Column(name = "nota_organizacao")
	private Double notaOrganizacao;
	
	@Column(name = "nota_atividades")
	private Double notaAtividades;
	
	@Column(name = "nota_apresentacao")
	private Double notaApresentacao;
}
