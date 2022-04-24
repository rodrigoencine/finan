package com.example.financeiro.event;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenericEvent <K, T>{
	private K id;
	private T event;
	private HttpServletResponse response;
	
	
}
