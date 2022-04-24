package com.example.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financeiro.model.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Integer>{
	
}
