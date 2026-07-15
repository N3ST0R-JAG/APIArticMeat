package com.articmeat.apicompras.controllers;

import com.articmeat.apicompras.models.*;
import com.articmeat.apicompras.services.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
		Usuario registrado = usuarioService.registrarUsuario(usuario);
		return ResponseEntity.ok(registrado);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, Object> credenciales){
	    Integer ci = (Integer) credenciales.get("ci");
	    String contrasena = (String) credenciales.get("contrasena");

	    Usuario usuario = usuarioService.obtenerPorCi(ci);

	    if(usuario == null){
	        return ResponseEntity.status(404).body("Usuario no encontrado");
	    }

	    if(!usuario.getContrasena().equals(contrasena)){
	        return ResponseEntity.status(401).body("Contraseña incorrecta");
	    }

	    return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Integer id){
	Usuario usuario = usuarioService.obtenerPorId(id);
	if(usuario != null) {
		return ResponseEntity.ok(usuario);
	}
	return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/ci/{ci}")
	public ResponseEntity<?> obtenerPorCi(@PathVariable Integer ci){
		Usuario usuario = usuarioService.obtenerPorCi(ci);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Usuario> usuarios = usuarioService.obtenerTodos();
		return ResponseEntity.ok(usuarios);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
		usuario.setId(id);
		Usuario actualizado = usuarioService.actualizarUsuario(usuario);
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		usuarioService.eliminarUsuario(id);
		return ResponseEntity.ok("Usuario Eliminado");
	}
}
