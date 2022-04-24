package com.example.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.financeiro.model.Compra;
import com.example.financeiro.model.Usuario;
import com.example.financeiro.queryDto.CompraQuery;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

	
	@Transactional(readOnly = true)
	Compra findByProcesso(String processo);
	
	@Query("SELECT NEW com.example.financeiro.queryDto.CompraQuery (c.processo, u.nome , cred.produto) "
			+ "from Compra c inner join c.usuario u inner join c.credito cred")
	Page<CompraQuery > search(@Param ("processo") String processo,@Param("nome") String nome,@Param ("produto") String produto,Pageable pageable);
	
	
	Page<Compra> findByUsuario(Usuario usuario, Pageable pageable);

}
