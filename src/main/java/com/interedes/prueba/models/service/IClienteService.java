package com.interedes.prueba.models.service;

import java.util.List;

import com.interedes.prueba.models.entity.Cliente;

public interface IClienteService {

	
	public Cliente findById(Long id);
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	
	
}
