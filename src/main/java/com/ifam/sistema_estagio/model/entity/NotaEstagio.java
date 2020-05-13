package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotaEstagio {
	
	@Column(name = "nota_conhecimento")
	private Integer notaConhecimento;

	@Column(name = "nota_organizacao")
	private Integer notaOrganizacao;
	
	@Column(name = "nota_atividades")
	private Integer notaAtividades;
	
	@Column(name = "nota_apresentacao")
	private Integer notaApresentacao;

	public Integer getNotaConhecimento() {
		return notaConhecimento;
	}

	public void setNotaConhecimento(Integer notaConhecimento) {
		this.notaConhecimento = notaConhecimento;
	}

	public Integer getNotaOrganizacao() {
		return notaOrganizacao;
	}

	public void setNotaOrganizacao(Integer notaOrganizacao) {
		this.notaOrganizacao = notaOrganizacao;
	}

	public Integer getNotaAtividades() {
		return notaAtividades;
	}

	public void setNotaAtividades(Integer notaAtividades) {
		this.notaAtividades = notaAtividades;
	}

	public Integer getNotaApresentacao() {
		return notaApresentacao;
	}

	public void setNotaApresentacao(Integer notaApresentacao) {
		this.notaApresentacao = notaApresentacao;
	}
}
