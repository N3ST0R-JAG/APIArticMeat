package com.articmeat.apicompras.services;

import com.articmeat.apicompras.models.Compras;
import com.articmeat.apicompras.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	public Compras registrarCompras(Compras compra) {
		return compraRepository.save(compra);
	}
	
	public List<Compras> obtenerCompraPorUsuario(Integer usuarioID) {
		return compraRepository.findByUsuarioID(usuarioID);
	}
	
	public List<Compras> obtenerTodasLasCompras(){
		return compraRepository.findAll();
	}
	
	public Compras obtenerCompraPorID(Integer id) {
		return compraRepository.findById(id).orElse(null);
	}
	
	public Compras actualizarCompra(Compras compra) {
		return compraRepository.save(compra);
	}
	
	public void eliminarCompra(Integer id) {
		compraRepository.deleteById(id);
	}
}

