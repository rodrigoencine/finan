package com.example.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.financeiro.model.NotaFiscal;


@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer>{
	@Transactional(readOnly = true)
	NotaFiscal findByNumero(String numero);
}
