package com.example.financeiro.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeiro.dto.NotaFiscalDto;
import com.example.financeiro.dto.ProdutoDto;
import com.example.financeiro.event.CreateEvent;
import com.example.financeiro.model.NotaFiscal;
import com.example.financeiro.model.Produto;
import com.example.financeiro.service.NotaFiscalService;
import com.example.financeiro.service.ProdutoService;

@RestController
@RequestMapping(value = "/notafiscal")
public class NotaFiscalResource {
	
	@Autowired
	private NotaFiscalService service;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ApplicationEventPublisher publischer;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<NotaFiscal> insert (@Valid @RequestBody NotaFiscalDto dto, HttpServletResponse response){
		NotaFiscal nf = this.service.create(dto);
		publischer.publishEvent(new CreateEvent(nf.getId_nota(), nf, response));
		return ResponseEntity.status(HttpStatus.CREATED).body(nf);

	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<NotaFiscal>> notas (){
		return ResponseEntity.ok().body(this.service.notas());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<NotaFiscal> findNotaFiscal(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.service.nota(id));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/produto/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Integer id){
		this.produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/produto/{id}")
	public ResponseEntity<Produto> update (@Valid @RequestBody ProdutoDto dto, @PathVariable Integer id){
		return ResponseEntity.ok().body(this.produtoService.atualiza(dto, id));
	}
	

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteNota(@PathVariable Integer id){
		this.service.deletarNotaFiscal(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<NotaFiscal> updateNota (@Valid @RequestBody NotaFiscalDto dto, @PathVariable Integer id){
		return ResponseEntity.ok().body(this.service.update(dto, id));
	}
}
