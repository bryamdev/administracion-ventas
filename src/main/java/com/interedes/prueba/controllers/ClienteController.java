package com.interedes.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interedes.prueba.models.dto.Response;
import com.interedes.prueba.models.entity.Cliente;
import com.interedes.prueba.models.service.IClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		Response response = new Response();
		try {
			List<Cliente> clientes = clienteService.findAll();
			response.setOk(true);
			response.setResults(clientes);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar listar los usuarios");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		
	}
	
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@RequestBody Cliente cliente){
		
		Response response = new Response();
		
		try {
			Cliente clienteNew = clienteService.save(cliente);
			response = new Response(true, "Cliente creado con exito", clienteNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar crear el usuario");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	
	
	
	
	
	
	@GetMapping("/prueba")
	public String prueba() {
		
		System.out.println("Si");
		return "saludo";
	}
}
