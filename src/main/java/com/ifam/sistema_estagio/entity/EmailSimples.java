package com.ifam.sistema_estagio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSimples {

//	http://commons.apache.org/proper/commons-email/userguide.html <- Modelo de email's

	private String from;
	private String username;
	private String password;
	private String to;
	private String subject;
	private String message;
	private String hostName;
	private Integer smtpPort;
	private Boolean sslInConnection;
}
