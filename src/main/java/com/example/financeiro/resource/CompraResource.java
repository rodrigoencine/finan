package com.example.financeiro.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeiro.dto.CompraDto;
import com.example.financeiro.event.CreateEvent;
import com.example.financeiro.model.Compra;
import com.example.financeiro.queryDto.CompraQuery;
import com.example.financeiro.service.CompraService;


@RestController
@RequestMapping(value = "/compra")
public class CompraResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Compra> create (@Valid @RequestBody CompraDto compraDto, HttpServletResponse response){
		Compra compra = this.compraService.create(compraDto);
		publisher.publishEvent(new CreateEvent(compra.getId_compra(), compra, response));
		return ResponseEntity.status(HttpStatus.CREATED).body(compra);
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Compra> findCompra(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.compraService.findCompra(id));
				
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteCompra(@PathVariable Integer id){
		this.compraService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public ResponseEntity<Compra> updateSetor (@Valid @RequestBody CompraDto setorDto, @PathVariable Integer id){
//		Compra setor = this.compraService.atualizar(setorDto, id);
//		return ResponseEntity.ok().body(setor);
//	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/page")
	public ResponseEntity<Page<Compra>> findAllPage(
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="processo") String orderBy,
			@RequestParam (value = "direction", defaultValue="ASC") String direction)
			
	{
		return ResponseEntity.ok().body(
				this.compraService.findPages(page, linesPerPage, orderBy, direction)
		);
				
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/all")
	public ResponseEntity<Page<CompraQuery >> findAll(
			@RequestParam (value = "processo", defaultValue = "")String processo,
			@RequestParam (value = "nome", defaultValue = "")String nome,
			@RequestParam (value = "produto", defaultValue = "")String produto,
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="processo") String orderBy,
			@RequestParam (value = "direction", defaultValue="ASC") String direction)
			
	{
		return ResponseEntity.ok().body(
				this.compraService.findPage(processo,nome, produto,page, linesPerPage, orderBy, direction)
		);
				
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/page/compras")
	public ResponseEntity<Page<Compra>> FindPageCompras(
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="processo") String orderBy,
			@RequestParam (value = "direction", defaultValue="ASC") String direction)
			
	{
		Page<Compra> list = this.compraService.findCompraUsuario(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
				
				
	}
}
