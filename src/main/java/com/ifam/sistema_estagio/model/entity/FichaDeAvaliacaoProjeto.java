package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;
import javax.persistence.Table;

@Entity
@Table(name = "ficha_de_avaliacao_projeto")
public class FichaDeAvaliacaoProjeto extends FichaDeAvaliacao{

	//Avaliador
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	//Ata a qual pertence
	@ManyToOne
	@JoinColumn(name = "ata_id", nullable = false)
	private Ata ata;
	
	@Embedded
	private NotaProjetoDefesa notaDefesa;

	@Embedded
	private NotaProjetoTrabalho notaTrabalho;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public NotaProjetoDefesa getNotaDefesa() {
		return notaDefesa;
	}

	public void setNotaDefesa(NotaProjetoDefesa notaDefesa) {
		this.notaDefesa = notaDefesa;
	}

	public NotaProjetoTrabalho getNotaTrabalho() {
		return notaTrabalho;
	}

	public void setNotaTrabalho(NotaProjetoTrabalho notaTrabalho) {
		this.notaTrabalho = notaTrabalho;
	}
}
