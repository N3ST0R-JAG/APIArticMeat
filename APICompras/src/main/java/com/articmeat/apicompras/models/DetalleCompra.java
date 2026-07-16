package com.articmeat.apicompras.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "detalle_compras")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("compraID")
    @Column(nullable = false)
    private Integer compraID;

    @JsonProperty("productoNombre")
    @Column(name = "productoNombre", nullable = false)
    private String productoNombre;

    @JsonProperty("cantidad")
    @Column(nullable = false)
    private Integer cantidad;

    @JsonProperty("precio")
    @Column(nullable = false)
    private Float precio;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCompraID() { return compraID; }
    public void setCompraID(Integer compraID) { this.compraID = compraID; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }
}
