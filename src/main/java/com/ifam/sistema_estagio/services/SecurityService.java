package com.ifam.sistema_estagio.services;

public interface SecurityService {
	
	String findLoggedinUsername();
	
	void autoLogin(String username, String password);
}
