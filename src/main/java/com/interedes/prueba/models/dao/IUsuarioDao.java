package com.interedes.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.interedes.prueba.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	
	public Usuario findByUsername(String username);
	
}
