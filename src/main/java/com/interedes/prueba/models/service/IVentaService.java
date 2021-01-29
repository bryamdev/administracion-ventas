package com.interedes.prueba.models.service;

import java.util.List;

import com.interedes.prueba.models.entity.Venta;

public interface IVentaService {

	
	public Venta findById(Long id);
	
	
	public List<Venta> findAll();
	
	
	public Venta save(Venta venta);
	
	
}
