package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ata")
public class Ata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "media_total")
	private Integer mediaTotal;

	@Column(name = "descricao")
	private String descricao;

	@Column(nullable = false, name = "tipo", length = 1)
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;

	//Fichas de estágio - Opcional
	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoEstagio> fichasEstagio;

	//Fichas de projeto - Opcional
	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoProjeto> fichasProjeto;
	
	//Banca correspondente
	@OneToOne
	private Banca banca;
}
