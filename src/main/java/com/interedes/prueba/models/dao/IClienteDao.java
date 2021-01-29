package com.interedes.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.interedes.prueba.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
