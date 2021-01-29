package com.interedes.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interedes.prueba.models.dto.Response;
import com.interedes.prueba.models.entity.Producto;
import com.interedes.prueba.models.service.IProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping(name = "/listar")
	public ResponseEntity<?> listar(){
		
		Response response = new Response();
		try {
			List<Producto> productos = productoService.findAll();
			response.setOk(true);
			response.setResults(productos);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar listar los productos");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
			
	}
	
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@RequestBody Producto producto){
		
		Response response = new Response();
		
		try {
			Producto productoNew = productoService.save(producto);
			response = new Response(true, "Producto creado con exito", productoNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar crear el producto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
			
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Producto producto, @PathVariable Long id){
		
		Response response = new Response();
		Producto productoOld = productoService.findById(id);
		
		if(productoOld == null) {
			response.setOk(false);
			response.setMessage("No exite un producto con el id: " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		try {
			producto.setIdProducto(id);
			Producto productoNew = productoService.save(producto);
			response = new Response(true, "Producto actualizado con exito", productoNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar actualizado el producto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
			
	}
	
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> borrar(@PathVariable Long id){
		
		Response response = new Response();
		Producto productoOld = productoService.findById(id);
		
		if(productoOld == null) {
			response.setOk(false);
			response.setMessage("No exite un producto con el id: " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		try {
			
			productoService.delete(id);
			response.setMessage("Producto eliminado con exito");
			response.setOk(true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setOk(false);
			response.setMessage("Ocurrio un error al intentar borrar el producto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	

	
	
	
	
}
