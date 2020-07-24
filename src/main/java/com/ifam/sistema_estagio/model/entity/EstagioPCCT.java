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

@Entity
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

	@Column(nullable = true, name = "trabalho")
	private byte[] trabalho;

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
	
	public EstagioPCCT() {
		
	}
	
	public EstagioPCCT(Integer id, String titulo, Integer cargaHoraria, Boolean concluido,String local, String  descricao, List<Banca> bancas,
			List<Aluno> alunos, Professor responsavel, byte[] trabalho) {
		this.id = id;
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.concluido = concluido;
		this.local = local;
		this.descricao = descricao;
		this.trabalho = trabalho;
		this.bancas = bancas;
		this.alunos = alunos;
		this.responsavel = responsavel;
	}

	public TipoServico getTipo() {
		return tipo;
	}

	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Professor responsavel) {
		this.responsavel = responsavel;
	}

	public byte[] getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(byte[] trabalho) {
		this.trabalho = trabalho;
	}
}