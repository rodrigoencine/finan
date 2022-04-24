package com.example.financeiro.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.financeiro.dto.SetorDto;
import com.example.financeiro.repository.SetorRepository;
import com.example.financeiro.resource.exception.FieldMessage;

public class SetorValidator implements ConstraintValidator<SetorInsert, SetorDto>{

	@Autowired
	private SetorRepository repository;
	
	@Override
	public boolean isValid(SetorDto dto, ConstraintValidatorContext context) {
		List<FieldMessage> errors = new ArrayList<>();
		
		if(this.repository.findBySigla( dto.getSigla()) !=null) {
			errors.add(new FieldMessage("sigla" , "Setor ja cadastrado"));
		}
		
		if(this.repository.findByDescricao(dto.getDescricao())!=null){
			errors.add(new FieldMessage("descricao" , "Setor ja cadastrado"));
		}
		
		for (FieldMessage e : errors) {
			context.disableDefaultConstraintViolation();
			
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return errors.isEmpty();
	}
	
}
