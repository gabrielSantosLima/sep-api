package com.ifam.sistema_estagio.controller.service;

public interface SecurityService {
	
	String findLoggedinUsername();
	
	void autoLogin(String username, String password);
}
