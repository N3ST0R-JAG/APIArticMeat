package com.articmeat.apicompras.controllers;

import com.articmeat.apicompras.models.DetalleCompra;
import com.articmeat.apicompras.services.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalle")
@CrossOrigin(origins = "*")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody DetalleCompra detalle) {
        DetalleCompra guardado = detalleCompraService.registrar(detalle);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/compra/{compraID}")
    public ResponseEntity<?> obtenerPorCompra(@PathVariable Integer compraID) {
        List<DetalleCompra> detalles = detalleCompraService.obtenerPorCompraID(compraID);
        return ResponseEntity.ok(detalles);
    }
}