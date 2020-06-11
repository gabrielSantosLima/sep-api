package com.ifam.sistema_estagio.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.ifam.sistema_estagio.util.enums.TipoServico;

@Entity
@DiscriminatorValue(TipoServico.Values.PROJETO)
public class Projeto extends EstagioPCCT{

	@Column(nullable = false, name = "descricao")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
