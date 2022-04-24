package com.example.financeiro.components;

import java.net.URI;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.financeiro.event.GenericEvent;

@Component
public class onEventListener <K, T>{
	
	@EventListener
	private void addHeader(GenericEvent<K,T> e) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(e.getId()).toUri();
		e.getResponse().setHeader("location", uri.toASCIIString());
		
	}
	
}
