package com.example.financeiro.resource.exception;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.financeiro.service.exceptions.AuthorizationException;
import com.example.financeiro.service.exceptions.DataIntegrityException;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceException {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,HttpServletRequest request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), 
											  "Erro de validação",
											  System.currentTimeMillis());
		for(FieldError field : e.getBindingResult().getFieldErrors()) {
		    err.addError(field.getField(), field.getDefaultMessage());
		}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
		
	}
	
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e , HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), 
												 e.getMessage(), 
												 System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
				
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation (DataIntegrityViolationException  e,HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), 
											  e.getMessage(),
											  System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), 
				  e.getMessage(),
				  System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ConstraintViolationException .class)
	public ResponseEntity<StandardError> constraintViolationException (ConstraintViolationException e,HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), 
											  "campo unico",
											  System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> objectNotFound(AuthorizationException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), 
				  e.getMessage(),
				  System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
