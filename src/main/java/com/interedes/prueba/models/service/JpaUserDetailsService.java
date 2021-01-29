package com.interedes.prueba.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interedes.prueba.models.dao.IUsuarioDao;
import com.interedes.prueba.models.entity.Role;
import com.interedes.prueba.models.entity.Usuario;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			log.error("Error: el usuario '" + username + "' no existe en la base de datos!");
			throw new UsernameNotFoundException("Error: el usuario '" + username + "' no existe en la base de datos!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : usuario.getRoles()) {
			
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
			authorities.add(authority);
			
			log.info("El usuario tiene el Rol: " + role.getAuthority());	
		}
		
		if(authorities.isEmpty()) {
			log.error("Error: el usuario '" + usuario.getUsername() + "' no tiene roles asignados!");
			throw new UsernameNotFoundException("Error: el usuario '" + usuario.getUsername() + "' no tiene roles asignados!");
		}
		
		UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), authorities);
		
		return userDetails;
	}

}
