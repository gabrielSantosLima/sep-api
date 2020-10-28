package com.ifam.sistema_estagio.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ifam.sistema_estagio.util.enums.Curso;
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
@Table(name = "banca")
public class Banca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false, name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@Column(nullable = false, name = "banca_final")
	private Boolean banca_final;
	
	@Column(nullable = false, name = "local")
	private String local;

	@Column(nullable = false, name = "hora_inicio")
	@Temporal(TemporalType.DATE)
	private Date horaInicio;

	@Column(nullable = false, name = "hora_finalizado")
	@Temporal(TemporalType.DATE)
	private Date horaFinalizado;

	//Ata
	@OneToOne
	private Ata ata;
	
	//Avaliadores da banca
	@OneToMany(mappedBy = "banca")
	private List<Avaliadores> avaliadores;

	//Coordenadora
	@ManyToOne
	@JoinColumn(name = "coordenadora_id")
	private Coordenadora coordenadora;

	//Estágio ou projeto que pertence
	@ManyToOne
	@JoinColumn(name = "estagio_pcct_id")
	private EstagioPCCT estagioPcct;
}
