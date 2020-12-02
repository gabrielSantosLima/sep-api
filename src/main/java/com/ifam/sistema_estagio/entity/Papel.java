package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "papeis")
public class Papel {

	@Id
	@GeneratedValue(generator = IdentificadorHexadecimalGenerator.nome)
	@GenericGenerator(
			name = IdentificadorHexadecimalGenerator.nome,
			strategy = "com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator"
	)
	@Column(length = ManipularNumerosHexadecimais.TAMANHO_NUMERO_ALEATORIO)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;

	@JsonBackReference
	@ManyToMany
	@JoinTable(
			  name = "papel_funcao", 
			  joinColumns = @JoinColumn(name = "papel_id"), 
			  inverseJoinColumns = @JoinColumn(name = "funcao_id"))
	private List<Funcao> funcoes;
}
