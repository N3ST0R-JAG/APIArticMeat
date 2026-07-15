package com.articmeat.apicompras.models;
import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private Float precio;
	
	@Column(nullable = false)
	private Integer stock;
	
	@Column(nullable = false)
	private String categoria;
	
	@Column(nullable = false)
	private LocalDate fecha_vencimiento;
	
	@Column(nullable = false)
	private LocalDate fecha_importe;

	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public Float getPrecio() {return precio;}
	public void setPrecio(Float precio) {this.precio = precio;}
	
	public Integer getStock() {return stock;}
	public void setStock(Integer stock) {this.stock = stock;}

	public String getCategoria() {return categoria;}
	public void setCategoria(String categoria) {this.categoria = categoria;}
	
	public LocalDate getFecha_vencimiento() {return fecha_vencimiento;}
	public void setFecha_vencimiento(LocalDate fecha_vencimiento) {this.fecha_vencimiento = fecha_vencimiento;}

	public LocalDate getFecha_importe() {return fecha_importe;}
	public void setFecha_importe(LocalDate fecha_importe) {this.fecha_importe = fecha_importe;}
}
