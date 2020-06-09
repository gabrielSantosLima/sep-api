package com.ifam.sistema_estagio.model.entity;

import java.util.Date;

public class Certificado {

	private String nome;

	/*
	 * Certificamos para os devidos fins de direito que RONALDO COSTA DE FREITAS
	 * atuou como Desenvolvedor Back-End no Projeto de Extensão - Fábrica de
	 * Software do CMC, promovido pelo Instituto Federal de Educação, Ciência e
	 * Tecnologia.
	 */
	private String complemento;

	private String emissor;
	private Date dataEmissao;

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}
}
