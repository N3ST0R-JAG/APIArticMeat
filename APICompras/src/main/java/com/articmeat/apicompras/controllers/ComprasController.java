package com.articmeat.apicompras.controllers;

import com.articmeat.apicompras.models.*;
import com.articmeat.apicompras.services.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class ComprasController {
	
	@Autowired
	private CompraService compraService;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Compras compra){
		Compras registrada = compraService.registrarCompras(compra);
		return ResponseEntity.ok(registrada);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorID(@PathVariable Integer id){
		Compras compra = compraService.obtenerCompraPorID(id);
		if(compra != null) {
			return ResponseEntity.ok(compra);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/usuario/{usuarioID}")
	public ResponseEntity<?> obtenerPorUsuario(@PathVariable Integer usuarioID){
		List<Compras> compra = compraService.obtenerCompraPorUsuario(usuarioID);
		return ResponseEntity.ok(compra);
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerTodas(){
		List<Compras> compra = compraService.obtenerTodasLasCompras();
		return ResponseEntity.ok(compra);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Compras compra){
		compra.setId(id);
		Compras actualizada = compraService.actualizarCompra(compra);
		return ResponseEntity.ok(actualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		compraService.eliminarCompra(id);
		return ResponseEntity.ok("Compra Eliminada");
	}
}
