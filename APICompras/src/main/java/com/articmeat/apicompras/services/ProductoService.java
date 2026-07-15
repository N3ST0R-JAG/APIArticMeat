package com.articmeat.apicompras.services;

import com.articmeat.apicompras.models.Productos;
import com.articmeat.apicompras.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository usuarioRepository;
	
	public Productos registrarProducto(Productos productos) {
		return usuarioRepository.save(productos);
	}
	
	public Productos obtenerPorID(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public List<Productos> obtenerTodos(){
		return usuarioRepository.findAll();
	}
	
	public Productos actualizarProducto(Productos productos) {
		return usuarioRepository.save(productos);
	}
	
	public void eliminarProducto(Integer id) {
		usuarioRepository.deleteById(id);
	}
}
