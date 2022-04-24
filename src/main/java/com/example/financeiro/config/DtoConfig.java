package com.example.financeiro.config;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.financeiro.dto.CompraDto;
import com.example.financeiro.dto.UsuarioDto;
import com.example.financeiro.model.Compra;
import com.example.financeiro.model.Usuario;


@Configuration
public class DtoConfig {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Bean
	public ModelMapper modelMapper() {
		var model = new ModelMapper();
		
//		Converter<String, String> toUppercase =
//			    ctx -> ctx.getSource() == null ? null : ctx.getSource().toUpperCase();
		 
		model.createTypeMap(UsuarioDto.class, Usuario.class)
		.addMappings(mapper -> mapper.map(UsuarioDto::getSetor, Usuario::setSetor));
		
		model.createTypeMap(CompraDto.class, Compra.class)
		.addMappings(mapper -> mapper.map(CompraDto::getSetor, Compra::setSetor))
		.addMappings(mapper -> mapper.map(CompraDto::getUsuario, Compra::setUsuario))
		.addMappings(mapper -> mapper.map(CompraDto::getCredito, Compra::setCredito));
		
//		Converter<String, String> cripto = ctx -> ctx.getSource() == null ? null : ctx.getSource().toUpperCase();
//		
//		model.createTypeMap(UsuarioDto.class, Usuario.class)
//		.addMappings(mapper -> mapper.using(cripto)
//		.map(UsuarioDto::getNome, Usuario::setNome));
		return model;
	}
}
