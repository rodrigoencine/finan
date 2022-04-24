package com.example.financeiro.repository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financeiro.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly = true)
	Usuario findByMatricula(String matricula);
	
	@Transactional(readOnly = true)
	Usuario findByEmail (String email);

}
