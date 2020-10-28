package com.ifam.sistema_estagio.services;

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

import com.ifam.sistema_estagio.entity.Papel;
import com.ifam.sistema_estagio.entity.UsuarioLogavel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CoordenadoraService coordenadoraService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AlunoService alunoService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioLogavel usuario;
		
		try{
			usuario = coordenadoraService.findByUsername(username).get();
			
			if (usuario == null) {
				usuario = professorService.findByUsername(username).get();
			}
			
			if(usuario == null) {
				usuario = alunoService.findByUsername(username).get();
			}
			
		}catch(Exception e) {
			throw new UsernameNotFoundException(username);			
		}
		
		if(usuario == null) {
			throw new UsernameNotFoundException(username);			
		}
		
		Set<GrantedAuthority> grantedAuthority = getGrantedAuthorities(usuario.getPapel());

		return new User(usuario.getUsername(), usuario.getPassword(), grantedAuthority);
	}
	
	private Set<GrantedAuthority> getGrantedAuthorities(Papel papel) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(papel.getNome()));
			
		papel.getFuncoes().stream().forEach(funcao -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(funcao.getNome()));
		});
		
		return grantedAuthorities;
	}
}
