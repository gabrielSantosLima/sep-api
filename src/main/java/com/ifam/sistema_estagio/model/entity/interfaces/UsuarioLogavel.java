package com.ifam.sistema_estagio.model.entity.interfaces;

public interface UsuarioLogavel {
	
	String getUsername();

	String getPassword();
	
	String getPasswordConfirm();
	
	void setUsername(String username);

	void setPassword(String password);

	void setPasswordConfirm(String passwordConfirm);
}
