package com.articmeat.apicompras.models;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonProperty("ci")
	@Column(nullable = false, unique = true)
	private Integer ci;
	
	@JsonProperty("nombre")
	@Column(nullable = false)
	private String nombre;
	
	@JsonProperty("email")
	@Column(nullable = false, unique = true)
	private String correo;
	
	@JsonProperty("contrasena")
	@Column(nullable = false)
	private String contrasena;
	
	@JsonProperty("direccion")
	@Column(nullable = false)
	private String direccion;
	
	@JsonProperty("tipoEmpresa")
	@Column(nullable = false)
	private String tipoEmpresa;
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	
	public Integer getCi() {return ci;}
	public void setCi(Integer ci) {this.ci = ci;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public String getEmail() {return correo;}
	public void setEmail(String email) {this.correo = email;}
	
	public String getContrasena() {return contrasena;}
	public void setContrasena(String contrasena) {this.contrasena = contrasena;}
	
	public String getTipoEmpresa() {return tipoEmpresa;}
	public void setTipoEmpresa(String telefono) {tipoEmpresa = telefono;}
	
	public String getDireccion() {return direccion;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
}