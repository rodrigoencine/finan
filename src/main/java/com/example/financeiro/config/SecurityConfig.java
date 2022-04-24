package com.example.financeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.financeiro.security.JWTAuthenticationFilter;
import com.example.financeiro.security.JWTAuthorizationFilter;
import com.example.financeiro.security.JWTUtil;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwt;
	
	private static final String [] PUBLIC_MATCHES = {
		
	};
	
	private static final String [] PUBLIC_MATCHES_GET = {
			
		};
	private static final String [] PUBLIC_MATCHES_POST = {
			"/usuario/**"
	};
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		//desabilitou proteçao de ataques csrf - Não armazenamos sessao (Stateless)
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHES_POST).permitAll()
		.antMatchers(PUBLIC_MATCHES).permitAll()
		.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwt));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwt, userDetailsService));
		//Não armazena sessão de usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	//Permissões de acesso ao end points, sendo chamado no metodo de config
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;	
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
