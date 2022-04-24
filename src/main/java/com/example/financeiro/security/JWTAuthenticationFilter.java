package com.example.financeiro.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.financeiro.dto.CredencialDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager maneger;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter (AuthenticationManager maneger,JWTUtil jwtUtil ) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.maneger = maneger;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		CredencialDto creds;
		try {

			//pega o post de login e depois converte no DTO
			creds = new ObjectMapper()
			        .readValue(request.getInputStream(), CredencialDto.class);
			
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        //esse metodo verifica se senha e email é valido
	        Authentication auth = maneger.authenticate(authToken);
	        
	  	  	return auth;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	//Metodo gera o token se deu tudo certo, ele recebo o objeto de autenticação que deu certo (Authentication) 
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//pega emmail
		String username = ((UsuarioSecurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
	}
	
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler{

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);;
			response.setContentType("application/json");
			response.getWriter().append(json());
			
		}
		
		private String json() {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ","
					+ "\" status\":  403, " 
					+ " \" error\": \" Não autorizado\", "
					+ "\" message\": \"email ou senha invalidos\","
					+ "\" path\": \"/login\"}";
						
		}
		
	}
	
}
