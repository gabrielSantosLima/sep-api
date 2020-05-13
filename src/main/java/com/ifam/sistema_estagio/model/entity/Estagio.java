package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estagio")
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
