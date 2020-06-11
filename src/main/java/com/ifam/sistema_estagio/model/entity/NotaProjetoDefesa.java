package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotaProjetoDefesa {

	@Column(name = "nota_slide")
	private Integer notaSlide;

	@Column(name = "nota_assunto")
	private Integer notaAssunto;

	@Column(name = "nota_clareza")
	private Integer notaClareza;

	@Column(name = "nota_linguagem")
	private Integer notaLinguagem;

	@Column(name = "nota_tempo")
	private Integer notaTempo;

	@Column(name = "nota_respotas")
	private Integer notaRespostas;

	public Integer getNotaSlide() {
		return notaSlide;
	}

	public void setNotaSlide(Integer notaSlide) {
		this.notaSlide = notaSlide;
	}

	public Integer getNotaAssunto() {
		return notaAssunto;
	}

	public void setNotaAssunto(Integer notaAssunto) {
		this.notaAssunto = notaAssunto;
	}

	public Integer getNotaClareza() {
		return notaClareza;
	}

	public void setNotaClareza(Integer notaClareza) {
		this.notaClareza = notaClareza;
	}

	public Integer getNotaLinguagem() {
		return notaLinguagem;
	}

	public void setNotaLinguagem(Integer notaLinguagem) {
		this.notaLinguagem = notaLinguagem;
	}

	public Integer getNotaTempo() {
		return notaTempo;
	}

	public void setNotaTempo(Integer notaTempo) {
		this.notaTempo = notaTempo;
	}

	public Integer getNotaRespostas() {
		return notaRespostas;
	}

	public void setNotaRespostas(Integer notaRespostas) {
		this.notaRespostas = notaRespostas;
	}
}
