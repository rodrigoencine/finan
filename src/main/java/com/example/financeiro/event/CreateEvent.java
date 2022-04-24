package com.example.financeiro.event;

import javax.servlet.http.HttpServletResponse;

public class CreateEvent extends GenericEvent<Integer, Object>{

	public CreateEvent(Integer id, Object setorCriado, HttpServletResponse response) {
		super(id,setorCriado, response);
		// TODO Auto-generated constructor stub
	}
	
}
