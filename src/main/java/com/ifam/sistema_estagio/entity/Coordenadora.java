package com.ifam.sistema_estagio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coordenadora")
public class Coordenadora extends Usuario{
	//Bancas
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;

	@OneToMany(mappedBy = "coordenadora")
	private List<NotificacaoBancas> notificacoesBancas;
}
