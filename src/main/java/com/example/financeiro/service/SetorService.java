package com.example.financeiro.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.financeiro.dto.SetorDto;
import com.example.financeiro.model.Setor;
import com.example.financeiro.repository.SetorRepository;
import com.example.financeiro.service.exceptions.DataIntegrityException;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@Service
public class SetorService {
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ModelMapper model; 
	
	
	@Transactional
	public Setor createSetor(SetorDto setorDto) { 	
		return this.setorRepository.save(this.toSetor(setorDto));
	}

	public Setor findSetor (Integer id) {
		return this.setorRepository
				.findById(id)
				.orElseThrow( () ->
					new ObjectNotFoundException("Setor não encontrado! id : " + id + "Tipo" + Setor.class.getName() )
				);
	}
	
	public void deleteSetor(Integer id) {
		this.findSetor(id);
		try {
			this.setorRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Setor vinculado");
		}
		
	}
	
	public Setor updateSetor(SetorDto dto, Integer id) {
		Setor setor = this.toSetor(dto);
		return this.setorRepository.save(setor);
	}
	
	public Setor toSetor(SetorDto dto) {
		return this.model.map(dto, Setor.class);
	}
}
