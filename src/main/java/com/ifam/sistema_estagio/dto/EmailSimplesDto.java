package com.ifam.sistema_estagio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSimplesDto{
	private String to;
	private String subject;
	private String message;
}
