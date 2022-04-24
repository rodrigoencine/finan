package com.example.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.financeiro.model.Usuario;
import com.example.financeiro.repository.UsuarioRepository;
import com.example.financeiro.security.UsuarioSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
	
		return new UsuarioSecurity(usuario.getId_usuario(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
