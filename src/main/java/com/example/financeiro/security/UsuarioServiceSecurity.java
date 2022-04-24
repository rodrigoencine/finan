package com.example.financeiro.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioServiceSecurity {
	
	public static UsuarioSecurity authenticated() {
		try {
			return (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
