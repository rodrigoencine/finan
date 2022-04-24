package com.example.financeiro.queryDto;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public class QueryCustoms {
	
	@Autowired
	private EntityManager maneger;
	
	public CompraQuery findPageMain() {
		String jpql = "select c from Compra c join c.usuario u";
		return null;
	}
	
	
}
