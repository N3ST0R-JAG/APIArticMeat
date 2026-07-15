package com.articmeat.apicompras.controllers;

import java.util.*;
import com.articmeat.apicompras.models.*;
import com.articmeat.apicompras.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductosController {

	@Autowired
	private ProductoService productoService;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Productos producto){
		Productos registrado = productoService.registrarProducto(producto);
		return ResponseEntity.ok(registrado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorID(@PathVariable Integer id){
		Productos producto = productoService.obtenerPorID(id);
		if(producto != null) {
			return ResponseEntity.ok(producto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Productos producto){
		producto.setId(id);
		Productos actualizado = productoService.actualizarProducto(producto);
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		productoService.eliminarProducto(id);
		return ResponseEntity.ok("Producto Eliminado");
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Productos> producto = productoService.obtenerTodos();
		return ResponseEntity.ok(producto);
	}
}
