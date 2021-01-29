package com.interedes.prueba.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interedes.prueba.models.dao.IVentaDao;
import com.interedes.prueba.models.entity.Venta;
import com.interedes.prueba.models.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	
	@Autowired
	private IVentaDao ventaDao;
	
	
	@Override
	public Venta findById(Long id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	@Override
	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		return (List<Venta>)ventaDao.findAll();
	}

	@Override
	public Venta save(Venta venta) {
		// TODO Auto-generated method stub
		return ventaDao.save(venta);
	}

}
