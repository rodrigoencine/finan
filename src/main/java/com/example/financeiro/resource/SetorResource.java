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

import com.example.financeiro.dto.SetorDto;
import com.example.financeiro.dto.SetorNewDto;
import com.example.financeiro.event.CreateEvent;
import com.example.financeiro.model.Setor;
import com.example.financeiro.repository.SetorRepository;
import com.example.financeiro.service.SetorService;

@RestController
@RequestMapping(value ="/setor")
public class SetorResource {

	@Autowired
	private SetorService setorService;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ApplicationEventPublisher publischer;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Setor> insert (@Valid @RequestBody SetorNewDto setorDto, HttpServletResponse response){
		Setor setor = setorService.createSetor(setorDto);
		publischer.publishEvent(new CreateEvent(setor.getId_setor(), setor, response));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(setor);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Setor> update (@Valid @RequestBody SetorDto setorDto, @PathVariable Integer id){
		Setor setor = this.setorService.updateSetor(setorDto,id);
		return ResponseEntity.ok().body(setor);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Setor> find (@PathVariable Integer id){
		Setor setor = setorService.findSetor(id);
		return ResponseEntity.ok().body(setor);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Setor> > findAll(){
		List<Setor> setoresDto = this.setorRepository.findAll();		
		return ResponseEntity.ok().body(setoresDto);
	}	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		this.setorService.deleteSetor(id);
		return ResponseEntity.noContent().build();
	}

}
