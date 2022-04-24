package com.example.financeiro.dto.outMapper;

import com.example.financeiro.enuns.Cargo;
import com.example.financeiro.model.Setor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMapper {
	private Integer id;
	private String nome;
	private String email;
	private String ramal;
	private Cargo cargo;
	private Setor setor;
	
}
