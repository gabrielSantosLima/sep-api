package com.ifam.sistema_estagio.reports.fields;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AtaEstagioFields{
	private String titulo;
	private String media;
	private String horaFinalizado;
	private String data;
	private String coordenadora;
	private String mensagem;
	private String membro_1;
	private String membro_2;
	private String aluno;
	private String curso;
	private String presidente;
	private String mensagemChefe;
}
