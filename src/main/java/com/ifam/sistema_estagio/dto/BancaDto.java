package com.ifam.sistema_estagio.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.Coordenadora;
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
	private Boolean banca_final;
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

		return Banca.builder()
				.curso(curso)
				.data(data)
				.local(local)
				.tipo(tipo)
				.horaFinalizado(horaFinalizado)
				.horaInicio(horaInicio)
				.ata(ata.construirEntidade())
				.estagioPcct(estagioPCCT.construirEntidade())
				.coordenadora(coordenadora)
				.banca_final(banca_final)
				.build();
	}
}
