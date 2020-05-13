package com.ifam.sistema_estagio.model.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ifam.sistema_estagio.util.enums.TipoServico;

@Entity
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

	@Column(nullable = false, name = "local")
	private String local;

	@Column(nullable = false, name = "hora_inicio")
	@Temporal(TemporalType.DATE)
	private Date horaInicio;

	@Column(nullable = false, name = "hora_finalizado")
	@Temporal(TemporalType.DATE)
	private Date horaFinalizado;

	//Avaliadores da banca
	@ManyToMany
    @JoinTable(
        name = "avaliadores",
        joinColumns = @JoinColumn(name = "banca_id"),
        inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
	private List<Professor> professores;
	
	// Estagio ou Projeto ao qual pertence
	@ManyToOne
	@JoinColumn(name = "estagio_id", nullable = false)
	private EstagioPCCT estagioPcct;

	// Ata gerada como avaliação do aluno
	@OneToOne
	@JoinColumn(name = "ata_id", nullable = false)
	private Ata ata;

	// Coordenadora organiza banca
	@ManyToOne
	@JoinColumn(name = "coordenadora_id", nullable = false)
	private Coordenadora coordenadora;

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Integer getId() {
		return id;
	}

	public EstagioPCCT getEstagioPcct() {
		return estagioPcct;
	}

	public void setEstagioPcct(EstagioPCCT estagioPcct) {
		this.estagioPcct = estagioPcct;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public Coordenadora getCoordenadora() {
		return coordenadora;
	}

	public void setCoordenadora(Coordenadora coordenadora) {
		this.coordenadora = coordenadora;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFinalizado() {
		return horaFinalizado;
	}

	public void setHoraFinalizado(Date horaFinalizado) {
		this.horaFinalizado = horaFinalizado;
	}
}
