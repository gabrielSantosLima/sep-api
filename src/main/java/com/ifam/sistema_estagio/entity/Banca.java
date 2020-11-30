package com.ifam.sistema_estagio.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.config.HexIdGenerator;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "banca")
public class Banca {

	@Id
	@GeneratedValue(generator = HexIdGenerator.nome)
	@GenericGenerator(
			name = HexIdGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.HexIdGenerator"
	)
	@Column(length = 24)
	private String id;

	@Column(nullable = false, name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false, name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@Column(nullable = false, name = "local")
	private String local;

	@Column(nullable = false, name = "hora_inicio")
	@Temporal(TemporalType.DATE)
	private Date horaInicio;

	@Column(nullable = false, name = "hora_finalizado")
	@Temporal(TemporalType.DATE)
	private Date horaFinalizado;

	@JsonManagedReference
	@OneToOne
	private Ata ata;

	@JsonBackReference
	@ManyToMany
	private List<Professor> avaliadores;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "coordenadora_id")
	private Coordenadora coordenadora;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "estagio_pcct_id")
	private EstagioPCCT estagioPcct;
}
