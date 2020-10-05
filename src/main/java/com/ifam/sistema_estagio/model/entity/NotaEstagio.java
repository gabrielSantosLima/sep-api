package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaEstagio {
	
	@Column(name = "nota_conhecimento")
	private Integer notaConhecimento;

	@Column(name = "nota_organizacao")
	private Integer notaOrganizacao;
	
	@Column(name = "nota_atividades")
	private Integer notaAtividades;
	
	@Column(name = "nota_apresentacao")
	private Integer notaApresentacao;
}
