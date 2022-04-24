package com.example.financeiro.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.financeiro.dto.UsuarioNewDto;
import com.example.financeiro.repository.UsuarioRepository;
import com.example.financeiro.resource.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDto>{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public boolean isValid(UsuarioNewDto userDto, ConstraintValidatorContext context) {
		List<FieldMessage> errors = new ArrayList<>();
		
		if(this.usuarioRepository.findByEmail(userDto.getEmail()) !=null){
			errors.add(new FieldMessage("email", "e-mail ja cadastrado"));
			
		}
		
		if(this.usuarioRepository.findByMatricula(userDto.getMatricula())!=null) {
			errors.add(new FieldMessage("Matricula", "Matricula ja cadastrada"));
		}
		
		for (FieldMessage e : errors) {
			context.disableDefaultConstraintViolation();
			
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return errors.isEmpty();
	}

}
