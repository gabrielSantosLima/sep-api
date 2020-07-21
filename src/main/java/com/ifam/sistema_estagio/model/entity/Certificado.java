package com.ifam.sistema_estagio.model.entity;

public class Certificado {

//	 Exemplo de complemento:
//	  
//	 Certificamos para os devidos fins de direito que RONALDO COSTA DE FREITAS
//	 atuou como Desenvolvedor Back-End no Projeto de Extensão - Fábrica de
//	 Software do CMC, promovido pelo Instituto Federal de Educação, Ciência e
//	 Tecnologia.
	 
	
	private String data;
	private String mensagem;
	
	public Certificado(String data, String mensagem) {
		super();
		this.data = data;
		this.mensagem = mensagem;
	}

	public Certificado() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
