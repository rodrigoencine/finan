package com.example.financeiro.resource;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeiro.dto.UsuarioDto;
import com.example.financeiro.dto.UsuarioNewDto;
import com.example.financeiro.dto.outMapper.UsuarioMapper;
import com.example.financeiro.event.CreateEvent;
import com.example.financeiro.model.Usuario;
import com.example.financeiro.service.UsuarioService;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ApplicationEventPublisher publischer;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert (@Valid @RequestBody UsuarioNewDto usuarioDto, HttpServletResponse response){
		Usuario user = this.usuarioService.createUsuario(usuarioDto);		
		publischer.publishEvent(new CreateEvent(user.getId_usuario(),user, response));
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
		//System.out.println(usuarioDto.getPerfis());
		//return null;
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Usuario> find (@PathVariable Integer id){
		return ResponseEntity.ok().body(this.usuarioService.findUser(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioMapper>> findAll(){
		return ResponseEntity.ok().body(this.usuarioService.listUsers());
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		this.usuarioService.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Usuario> upadte(@Valid @RequestBody UsuarioDto usuarioDto, @PathVariable Integer id){
		return ResponseEntity.ok()
				.body(this.usuarioService.updateUsuario(usuarioDto,id));
	}
}
