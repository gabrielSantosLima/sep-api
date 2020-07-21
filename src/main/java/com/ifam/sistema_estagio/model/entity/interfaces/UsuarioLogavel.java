package com.ifam.sistema_estagio.model.entity.interfaces;

import java.util.Set;

import com.ifam.sistema_estagio.model.entity.Role;

public interface UsuarioLogavel {
	
	String getUsername();

	String getPassword();
	
	String getPasswordConfirm();
	
	void setUsername(String username);

	void setPassword(String password);

	void setPasswordConfirm(String passwordConfirm);
	
	Set<Role> getRoles();

	void setRoles(Set<Role> roles);
}
