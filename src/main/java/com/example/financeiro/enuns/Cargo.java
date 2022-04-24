package com.example.financeiro.enuns;

import com.example.financeiro.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Cargo{
	Auxiliar (1, "Auxiliar"),
	Tecnico (2, "Tecnico"),
	Analista(3, "Analista");
	
	
	
	private Integer id_cargo;
	
	@JsonValue
	private String descricao;
		public static Cargo isCargo(Integer id) {
		for(Cargo cargo : Cargo.values()) {
			if(id.equals(cargo.getId_cargo())) {
				return cargo;
			}
		}
		throw new ObjectNotFoundException(
				"Cargo não existe! Id: " + id + " Tipo " + Cargo.class.getName());
	}
}
