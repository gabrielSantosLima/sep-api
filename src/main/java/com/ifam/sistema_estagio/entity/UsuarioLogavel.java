package com.ifam.sistema_estagio.entity;

public interface UsuarioLogavel {
	String getUsername();
	String getPassword();
	void setUsername(String username);
	void setPassword(String password);
	Papel getPapel();
	void setPapel(Papel papel);
}
