package com.ifam.sistema_estagio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;

import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alunos")
public class Aluno extends Usuario{

	@Column(nullable = false, name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@Column(nullable = false, name = "turma")
	private String turma;

	@Column(nullable = false, name = "modalidade_curso")
	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidadeCurso;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "estagioPcct_id")
	private EstagioPCCT estagioPcct;
}
