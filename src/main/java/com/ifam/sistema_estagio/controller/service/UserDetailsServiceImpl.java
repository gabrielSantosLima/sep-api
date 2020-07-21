package com.ifam.sistema_estagio.controller.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.model.entity.Role;
import com.ifam.sistema_estagio.model.entity.interfaces.UsuarioLogavel;
import com.ifam.sistema_estagio.model.repository.CoordenadoraRepository;
import com.ifam.sistema_estagio.model.repository.ProfessorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CoordenadoraRepository coordenadoraRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioLogavel usuario = coordenadoraRepository.findByUsername(username);

		if (usuario == null) {
			usuario = professorRepository.findByUsername(username);
		}

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Set<GrantedAuthority> grantedAuthority = new HashSet<>();

		for (Role role : usuario.getRoles()) {
			grantedAuthority.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new User(usuario.getUsername(), usuario.getPassword(), grantedAuthority);
	}
}
