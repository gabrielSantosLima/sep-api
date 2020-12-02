package com.ifam.sistema_estagio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSimplesDto{
	private String to;
	private String subject;
	private String message;
	private Map<String, Object> params;
}
