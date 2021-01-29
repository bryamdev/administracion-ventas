package com.interedes.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.interedes.prueba.models.entity.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long>{

}
