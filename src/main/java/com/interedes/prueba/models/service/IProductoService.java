package com.interedes.prueba.models.service;

import java.util.List;

import com.interedes.prueba.models.entity.Producto;

public interface IProductoService {
	
	
	public Producto findById(Long id);
	
	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	

}
