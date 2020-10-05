package com.ifam.sistema_estagio.model.entity.interfaces;

import com.ifam.sistema_estagio.model.entity.Papel;

public interface UsuarioLogavel {
	
	String getUsername();

	String getPassword();
	
	String getPasswordConfirm();
	
	void setUsername(String username);

	void setPassword(String password);

	void setPasswordConfirm(String passwordConfirm);
	
	Papel getPapel();

	void setPapel(Papel papel);
}
