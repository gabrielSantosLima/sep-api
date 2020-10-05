package com.ifam.sistema_estagio.model.entity;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estagio_pcct")
public class EstagioPCCT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, name = "titulo")
	private String titulo;

	@Column(nullable = false, name = "carga_horario")
	private Integer cargaHoraria;
	
	@Column(nullable = true, name = "concluido")
	private Boolean concluido;

	@Column(nullable = true, name = "local")
	private String local;
	
	@Column(nullable = true, name = "descricao")
	private String descricao;

	@Column(nullable = false, name = "tipo")
	@Enumerated(EnumType.ORDINAL)
	private TipoServico tipo;
	
	//Bancas
	@OneToMany
	private List<Banca> bancas;

	//Alunos
	@OneToMany(mappedBy = "estagioPcct")
	private List<Aluno> alunos;
	
	//Reponsável
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Professor responsavel;
}