package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "atas")
public class Ata {

	@Id
	@GeneratedValue(generator = IdentificadorHexadecimalGenerator.nome)
	@GenericGenerator(
			name = IdentificadorHexadecimalGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator"
	)
	@Column(length = ManipularNumerosHexadecimais.TAMANHO_NUMERO_ALEATORIO)
	private String id;

	@Column(name = "media_total")
	private Double mediaTotal;

	@Column(name = "descricao")
	private String descricao;

	@Column(nullable = false, name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;

	@JsonBackReference
	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoEstagio> fichasEstagio;

	@JsonBackReference
	@OneToMany(mappedBy = "ata")
	private List<FichaDeAvaliacaoProjeto> fichasProjeto;

	@JsonManagedReference
	@OneToOne
	private Banca banca;
}
