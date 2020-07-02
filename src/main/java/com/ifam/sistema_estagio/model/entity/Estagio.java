package com.ifam.sistema_estagio.model.entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ifam.sistema_estagio.util.enums.TipoServico;

@Entity
@DiscriminatorValue(TipoServico.Values.ESTAGIO)
public class Estagio extends EstagioPCCT {

	@Column(nullable = false, name = "local")
	private String local;
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
	