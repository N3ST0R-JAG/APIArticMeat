package com.articmeat.apicompras.models;
import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
@Table(name = "compras")
public class Compras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer usuarioID;
	
	@Column(nullable = false)
	private LocalDate fecha;
	
	@Column(nullable = false)
	private Float total;
	
	@Column(nullable = false)
	private String estado;

	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}

	public Integer getUsuarioID() {return usuarioID;}
	public void setUsuarioID(Integer usuarioID) {this.usuarioID = usuarioID;}

	public LocalDate getFecha() {return fecha;}
	public void setFecha(LocalDate fecha) {this.fecha = fecha;}
	
	public Float getTotal() {return total;}
	public void setTotal(Float total) {this.total = total;}

	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	
	
}
