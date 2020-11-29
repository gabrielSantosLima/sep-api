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

import com.ifam.sistema_estagio.config.HexIdGenerator;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ata")
public class Ata {

	@Id
	@GeneratedValue(generator = HexIdGenerator.nome)
	@GenericGenerator(
			name = HexIdGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.HexIdGenerator"
	)
	@Column(length = 24)
	private String id;

	@Column(name = "media_total")
	private Double mediaTotal;

	@Column(name = "descricao")
	private String descricao;

	@Column(nullable = false, name = "tipo", length = 1)
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;

	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoEstagio> fichasEstagio;

	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoProjeto> fichasProjeto;
	
	@OneToOne
	private Banca banca;
}
