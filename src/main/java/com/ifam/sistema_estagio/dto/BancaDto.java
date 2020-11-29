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
	private Date data;
	private TipoServico tipo;
	private Curso curso;
	private String local;
	private Date horaInicio;
	private Date horaFinalizado;
	private EstagioPCCTDto estagioPCCT;
	private List<UsuarioDto> participantes;
	private AtaDto ata;

	@Override
	public Banca construirEntidade() {
		Coordenadora coordenadora = participantes.stream()
				.filter(participante -> participante.getFuncao() == FuncaoEstagio.COORDENADOR)
				.findFirst()
				.get()
				.construirCoordenadora();

		List<Professor> avaliadores = participantes.stream()
				.filter(participante ->
						participante.getFuncao() == FuncaoEstagio.COLABORADOR ||
						participante.getFuncao() == FuncaoEstagio.ORIENTADOR ||
						participante.getFuncao() == FuncaoEstagio.COORDENADOR_ADJUNTO
				)
				.collect(Collectors.toList())
				.stream()
				.map(participante -> participante.construirProfessor())
				.collect(Collectors.toList());

		return Banca.builder()
				.curso(curso)
				.data(data)
				.local(local)
				.tipo(tipo)
				.horaFinalizado(horaFinalizado)
				.horaInicio(horaInicio)
				.ata(ata.construirEntidade())
				.coordenadora(coordenadora)
				.estagioPcct(estagioPCCT.construirEntidade())
				.avaliadores(avaliadores)
				.build();
	}
}
