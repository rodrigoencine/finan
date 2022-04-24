package com.example.financeiro.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> error = new ArrayList<>();
	
	public ValidationError(Integer status, String error, Long timestamp) {
		super(status, error, timestamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getError() {
		return this.error;
	}
	
	public void addError(String fiedName, String message) {
		error.add(new FieldMessage(fiedName, message));
	}

}
