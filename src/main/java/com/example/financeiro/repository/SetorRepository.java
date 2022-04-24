package com.example.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.financeiro.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer>{
	
	@Transactional(readOnly = true)
	Setor findBySigla(String sigla);
	
	@Transactional(readOnly = true)
	Setor findByDescricao(String descricao);
}
