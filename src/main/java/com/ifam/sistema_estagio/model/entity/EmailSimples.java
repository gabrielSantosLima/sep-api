package com.ifam.sistema_estagio.model.entity;

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
	
	public EmailSimples() {
		
	}

	public EmailSimples(String from, String username, String password, String to, String subject, String message,
			String hostName, Integer smtpPort, Boolean sslInConnection) {
		this.from = from;
		this.username = username;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.hostName = hostName;
		this.smtpPort = smtpPort;
		this.sslInConnection = sslInConnection;
	}


	public Integer getSmtpPort() {
		return smtpPort;
	}


	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}


	public Boolean getSslInConnection() {
		return sslInConnection;
	}


	public void setSslInConnection(Boolean sslInConnection) {
		this.sslInConnection = sslInConnection;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
