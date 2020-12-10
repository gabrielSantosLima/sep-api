package com.ifam.sistema_estagio.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BancaDto implements Serializable,IObjetoDto<Banca>{
	private String id;
	private Date data;
	private TipoServico tipo;
	private Curso curso;
	private String local;
	private Date horaInicio;
	private Date horaFinalizado;
	private EstagioPCCTDto estagioPCCT;
	private List<UsuarioDto> avaliadores;
	private UsuarioDto coordenadora;
	private AtaDto ata;

	public BancaDto(String id){
		this.id = id;
	}

	@Override
	public Banca construirEntidade() {
		List<Professor> avaliadoresEntidade = avaliadores.stream()
				.map(participante -> participante == null ? null: participante.construirProfessor())
				.collect(Collectors.toList());

		return Banca.builder()
				.id(id)
				.curso(curso)
				.data(data)
				.local(local)
				.tipo(tipo)
				.horaFinalizado(horaFinalizado)
				.horaInicio(horaInicio)
				.coordenadora(coordenadora == null ? null : coordenadora.construirCoordenadora())
				.estagioPcct(estagioPCCT == null? null : estagioPCCT.construirEntidade())
				.avaliadores(avaliadoresEntidade)
				.build();
	}
}
