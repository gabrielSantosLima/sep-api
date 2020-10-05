package com.ifam.sistema_estagio.model.entity;

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

import com.ifam.sistema_estagio.model.entity.interfaces.UsuarioLogavel;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno extends Usuario implements UsuarioLogavel{

	@Column(nullable = false, name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@Column(nullable = false, name = "modalidade_curso")
	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidadeCurso;

	@Column(nullable = true, name = "anexo")
	private byte[] anexo;
	
	//Estágio ou projeto
	@ManyToOne
	@JoinColumn(name = "estagioPcct_id")
	private EstagioPCCT estagioPcct;
	
	@ManyToOne
	@JoinColumn(name = "papel_id")
	private Papel papel;
}
