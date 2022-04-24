package com.example.financeiro.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.financeiro.dto.UsuarioDto;
import com.example.financeiro.dto.outMapper.UsuarioMapper;
import com.example.financeiro.enuns.Perfil;
import com.example.financeiro.model.Usuario;
import com.example.financeiro.repository.UsuarioRepository;
import com.example.financeiro.security.UsuarioSecurity;
import com.example.financeiro.security.UsuarioServiceSecurity;
import com.example.financeiro.service.exceptions.AuthorizationException;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public Usuario createUsuario (UsuarioDto usuarioDto) {
		return this.usuarioRepository.save(toUsuario(usuarioDto));
	}

	public Usuario findUser (Integer id) {
		
		UsuarioSecurity security = UsuarioServiceSecurity.authenticated();
		if(security == null || !security.hasRole(Perfil.ADMIN) && !id.equals(security.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> user = usuarioRepository.findById(id);
		return user.orElseThrow (() -> 
		new ObjectNotFoundException(
				"usuario não encontrado! Id: " + id + " Tipo " + Usuario.class.getName())

				);
	}
		
	public void deleteUsuario(Integer id) {
		this.findUser(id);
		try {
			this.usuarioRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Usuario não pode ser deletado");
		}
	}
	
	public Usuario updateUsuario(UsuarioDto dto, Integer id) {
		Usuario usuario = this.toUsuario(dto);
		return this.usuarioRepository.save(usuario);
	}
	
	public List<UsuarioMapper> listUsers (){
		return this.usuarioRepository.findAll().stream()
				.map(this::toUsuarioMapper).collect(Collectors.toList());
	}
	
	private UsuarioMapper toUsuarioMapper(Usuario usuario) {
		return  model.map(usuario, UsuarioMapper.class);
	}
	private Usuario toUsuario(UsuarioDto dto) {
		Usuario usuario = model.map(dto, Usuario.class);
		usuario.setSenha(encoder.encode(dto.getSenha()));
	
		return usuario;
	}
	
}
