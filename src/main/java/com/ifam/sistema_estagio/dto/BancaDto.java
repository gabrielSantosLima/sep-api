package com.ifam.sistema_estagio.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BancaDto implements Serializable {
	private String id;
	private Date data;
	private TipoServico tipo;
	private Curso curso;
	private Boolean banca_final;
	private String local;
	private Date horaInicio;
	private Date horaFinalizado;
	private EstagioPCCTDto estagioPCCT;
	private List<UsuarioDto> participantes;
}
