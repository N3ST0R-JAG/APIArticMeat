package com.articmeat.apicompras.services;

import com.articmeat.apicompras.models.Usuario;
import com.articmeat.apicompras.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario registrarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario obtenerPorId(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario obtenerPorCi(Integer ci) {
		return usuarioRepository.findByCi(ci);
	}
	
	public List<Usuario> obtenerTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void eliminarUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}
}
