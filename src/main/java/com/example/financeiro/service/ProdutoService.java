package com.example.financeiro.service;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.financeiro.dto.ProdutoDto;
import com.example.financeiro.model.NotaFiscal;
import com.example.financeiro.model.Produto;
import com.example.financeiro.repository.ProdutoRepository;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional(readOnly = true)
	public void create(List<Produto> p) {
		p.forEach(this.produtoRepository::save);
	}

	public Produto find(Integer id) {
		return this.produtoRepository.findById(id)
				.orElseThrow(() -> 
				new ObjectNotFoundException("produto não encontrado! id : " + id + "Tipo" + Produto.class.getName() )
						);
	}
	public Produto atualiza(ProdutoDto dto, Integer id) {
		Produto produto = this.find(id);
		BeanUtils.copyProperties(dto, produto);
		
		return this.produtoRepository.save(produto);
	}
	
	public void deletar(Integer id) {
		this.produtoRepository.findById(id).orElseThrow(() -> 
		new ObjectNotFoundException("Nota fiscal não encontrado! id : " + id + "Tipo" + NotaFiscal.class.getName() ));
		
		this.produtoRepository.deleteById(id);
	}

}
