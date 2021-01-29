package com.interedes.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.interedes.prueba.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
