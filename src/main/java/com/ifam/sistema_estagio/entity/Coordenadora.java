package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coordenadora")
public class Coordenadora extends Usuario{

	@JsonBackReference
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;

	@JsonBackReference
	@OneToMany(mappedBy = "coordenadora")
	private List<NotificacaoBancas> notificacoesBancas;
}
