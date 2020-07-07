package com.ifam.sistema_estagio.model.entity;

import java.util.List;
import java.util.Date;

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

	@Column(nullable = false, name = "banca_final")
	private Boolean banca_final;
	
	@Column(nullable = false, name = "local")
	private String local;

	@Column(nullable = false, name = "hora_inicio")
	@Temporal(TemporalType.DATE)
	private Date horaInicio;

	@Column(nullable = false, name = "hora_finalizado")
	@Temporal(TemporalType.DATE)
	private Date horaFinalizado;

	//Ata
	@OneToOne
	private Ata ata;
	
	//Avaliadores da banca
	@ManyToMany
	@JoinTable(
		name = "avaliadores",
    	joinColumns = @JoinColumn(name = "banca_id"),
    	inverseJoinColumns = @JoinColumn(name = "professor_id")
	)
	private List<Professor> professores;
	
	public Banca(Integer id, Date data, TipoServico tipo, Boolean banca_final, String local, Date horaInicio,
			Date horaFinalizado, Ata ata, List<Professor> professores, Coordenadora coordenadora,
			EstagioPCCT estagioPcct) {
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.banca_final = banca_final;
		this.local = local;
		this.horaInicio = horaInicio;
		this.horaFinalizado = horaFinalizado;
		this.ata = ata;
		this.professores = professores;
		this.coordenadora = coordenadora;
		this.estagioPcct = estagioPcct;
	}

	//Coordenadora
	@ManyToOne
	@JoinColumn(name = "coordenadora_id")
	private Coordenadora coordenadora;

	//Estágio ou projeto que pertence
	@ManyToOne
	@JoinColumn(name = "estagio_pcct_id")
	private EstagioPCCT estagioPcct;
	
	public Boolean getBanca_final() {
		return banca_final;
	}

	public void setBanca_final(Boolean banca_final) {
		this.banca_final = banca_final;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Coordenadora getCoordenadora() {
		return coordenadora;
	}

	public void setCoordenadora(Coordenadora coordenadora) {
		this.coordenadora = coordenadora;
	}

	public EstagioPCCT getEstagioPcct() {
		return estagioPcct;
	}

	public void setEstagioPcct(EstagioPCCT estagioPcct) {
		this.estagioPcct = estagioPcct;
	}

	public Integer getId() {
		return id;
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
