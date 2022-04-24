package com.example.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financeiro.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
