package com.ifam.sistema_estagio.entity;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "funcoes")
public class Funcao {

	@Id
	@GeneratedValue(generator = IdentificadorHexadecimalGenerator.nome)
	@GenericGenerator(
			name = IdentificadorHexadecimalGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator"
	)
	@Column(length = ManipularNumerosHexadecimais.TAMANHO_NUMERO_ALEATORIO)
	private String id;
	
	@Column(name = "nome")
	private String nome;

	@JsonBackReference
	@ManyToMany(mappedBy = "funcoes")
	private List<Papel> papeis;
	
	public Funcao(String nome) {
		this.nome = nome;
	}
}
